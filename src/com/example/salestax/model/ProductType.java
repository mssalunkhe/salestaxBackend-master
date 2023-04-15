package com.example.salestax.model;

public enum ProductType {


    FOOD {
        @Override
        public double getTaxRate(Boolean isImpoterd) {
            if(isImpoterd) return taxRate  + getImportDutyRate();
            return taxRate;
        }


        @Override
        public  void setTaxRate(double taxRate) {
            this.taxRate = taxRate;
        }
    },
    MEDICINE {
        @Override
        public double getTaxRate(Boolean isImpoterd) {

            if (isImpoterd) return taxRate + getImportDutyRate();
            return taxRate;

        }

        @Override
        public  void setTaxRate(double taxRate) {
            this.taxRate = taxRate;
        }

    },
    BOOK {
        @Override
        public double getTaxRate(Boolean isImpoterd) {

            if (isImpoterd) return taxRate + getImportDutyRate();
            return taxRate;

        }

        @Override

        public  void setTaxRate(double taxRate) {
            this.taxRate = taxRate;
        }

    },
    OTHER {
        @Override
        public double getTaxRate(Boolean isImpoterd) {

            if (isImpoterd) return taxRate + getImportDutyRate();
            return taxRate;


        }

        @Override
        public void setTaxRate(double taxRate) {
            this.taxRate = taxRate;
        }

    };

    double taxRate = 0.0;
    static double  importDutyRate=0.0;

    public static void setImportDutyRate( double Rate){
        importDutyRate = Rate;
    }
    public static double getImportDutyRate(){
        return importDutyRate;
    }


    public abstract double getTaxRate(Boolean isImpoterd);

    public abstract   void setTaxRate(double taxRate);


}
