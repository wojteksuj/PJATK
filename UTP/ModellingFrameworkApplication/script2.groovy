def CONS = new double[LL]

for (def i = 0; i < LL; i++) {
    def totalExpenditure = KI[i] + KS[i] + INW[i];
    CONS[i] = (KI[i] + KS[i]) / totalExpenditure;
}
binding.setVariable('CONS', CONS);
