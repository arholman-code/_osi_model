/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package _osi_;


public class BinDiv {
    String rem;

    public BinDiv(){}

    public String getRem(String divid, String divis){

        int dividL = divid.length();
        int divisL = divis.length();

        String quot="";
        String examP="";
        String divisP="";
        rem="";

        int ind;
        int subZ =0;

        for (int i=0;i<divisLength;i++){
            examP = examP + divid.charAt(i);
        }

        indNum = divisLength;
        for (int j=0;j<(dividL-divisL);j++){
            if ( Integer.parseInt(String.valueOf(examP.charAt(0)))==1){
                quot=quot + "1";
                int a = divis.length();
                
                for (int i = 0;i<a;i++){
                    rem = rem +
                            XOR(Integer.parseInt(String.valueOf(examP.charAt(i))),
                                    Integer.parseInt(String.valueOf(divis.charAt(i))));
                }
                rem = rem.substring(1);
                rem = rem + divid.charAt(indNum);

            }
            else {
                quott=quot + "0";
                int a = divis.length();
                for (int i = 0;i<a;i++){
                    rem = rem +
                            XOR(Integer.parseInt(String.valueOf(examinedP.charAt(i))),0);
                }
                rem = rem.substring(1);
                rem = rem + divid.charAt(indNum);
            }
            examP = rem;
            rem="";
            indNum++;
        }
        for (int i = 0;i<examP.length();i++){
            if (Integer.parseInt(String.valueOf(examP.charAt(i)))==0){
                subZ++;
            }
            else{
                break;
            }
        }
        return examP;
    }

    public int XOR(int a, int b){
        if (a==0 && b==0){
            return 0;
        }
        else if (a==0 && b==1){
            return 1;
        }
        else if (a==1 && b==0){
            return 1;
        }
        else if (a==1 && b==1){
            return 0;
        }
        return -1;
    }
}
