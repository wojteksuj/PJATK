import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public class Controller {
    Model model;
    List<String> years = new ArrayList<>();
    LinkedHashMap<String, Object> map = new LinkedHashMap<>();

    public Controller(String modelName) {
        try {
            Class<?> clazz = Class.forName(modelName);
            model = (Model) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load model: " + modelName, e);
        }
    }

    public Controller readDataFrom(String fname) {
        try (BufferedReader br = new BufferedReader(new FileReader(fname))) {
            int ll = 0;
            Map<String, List<Double>> data = new HashMap<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] temp = line.trim().split("\\s+");
                if (temp[0].equals("LATA")) {
                    years.addAll(Arrays.asList(temp).subList(1, temp.length));
                    ll = years.size();
                } else {
                    List<Double> l = new ArrayList<>();
                    for (int i = 1; i < temp.length; i++) l.add(Double.parseDouble(temp[i]));
                    data.put(temp[0], l);
                }
            }

            for (Field field : model.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Bind.class)) {
                    field.setAccessible(true);
                    String name = field.getName();
                    if(name.equals("LL")) field.set(model, ll);
                    if (field.getType().isArray() && data.containsKey(name)) {

                        double[] temp = new double[ll];
                        List<Double> values = data.get(name);

                        int a = values.size();
                        for (int i = 0; i < values.size(); i++) temp[i] = values.get(i);
                        for (int i = values.size(); i < ll; i++) temp[i] = values.get(a - 1);
                        field.set(model, temp);
                        map.put(name, temp);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to read data from file: " + fname, e);
        }

        return this;
    }

    public Controller runModel() {
        model.run();

        for (Field field : model.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Bind.class)) {
                field.setAccessible(true);
                try {
                    map.put(field.getName(), field.get(model));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return this;

    }

    public Controller runScript(String script) {
        Binding binding = new Binding(map);
        GroovyShell shell = new GroovyShell(binding);
        shell.evaluate(script);

        for (Object k : binding.getVariables().keySet()) {
            String key = (String) k;
            if (!map.containsKey(key)) {
                map.put(key, binding.getVariable(key));
            }
        }
        return this;
    }

    public Controller runScriptFromFile(String fname) {
        try (BufferedReader br = new BufferedReader(new FileReader(fname))) {
            String line;
            String script = "";
            while ((line = br.readLine()) != null) {
                script = script + line + '\n';
            }
            runScript(script);
        } catch (IOException e) {
            System.out.println("Error while running script from a file: " + e);
        }
        return this;
    }

    public String getResultsAsTsv() {
        StringBuilder sb = new StringBuilder();
        sb.append("LATA" + '\t');
        for (String l : years) sb.append(l + '\t');
        sb.append('\n');

        List<String> keys = new ArrayList<>(map.keySet());

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            if(!key.equals("LL")){sb.append(key.trim() + '\t');
                double[] temp = (double[]) map.get(key);
                for (double v : temp) {
                    sb.append(String.valueOf(Math.round(v * 100.0)/ 100.0) + '\t');
                }
                sb.append('\n');}
        }
        return sb.toString();
    }


}
