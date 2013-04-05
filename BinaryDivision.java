/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package _osi_;

/**
 *
 * @author arholman
 */
public class BinaryDivision {


    String substracted;

    public BinaryDivision(){

    }

    public String getRemainder(String dividend, String divisor){

        int dividendLength = dividend.length();
        int divisorLength = divisor.length();

        String quotient="";
        String examinedP="";
        String divisorP="";
        substracted="";

        int indexNumber;
        int substractZeros=0;


        for (int i=0;i<divisorLength;i++){
            examinedP = examinedP + dividend.charAt(i);
        }


        indexNumber = divisorLength;

        for (int j=0;j<(dividendLength-divisorLength);j++){

            //START
            if ( Integer.parseInt(String.valueOf(examinedP.charAt(0)))==1){
                quotient=quotient + "1";
                int a = divisor.length();
                
                for (int i = 0;i<a;i++){
                    substracted = substracted +
                            CRC_substract(Integer.parseInt(String.valueOf(examinedP.charAt(i))),
                                    Integer.parseInt(String.valueOf(divisor.charAt(i))));
                }
                substracted = substracted.substring(1);
                substracted = substracted + dividend.charAt(indexNumber);

            }
            else {
                quotient=quotient + "0";
                int a = divisor.length();
                
                for (int i = 0;i<a;i++){
                    substracted = substracted +
                            CRC_substract(Integer.parseInt(String.valueOf(examinedP.charAt(i))),
                                    0);
                }
                substracted = substracted.substring(1);
                substracted = substracted + dividend.charAt(indexNumber);
            }

            examinedP = substracted;
            substracted="";
            indexNumber++;
        }
        for (int i = 0;i<examinedP.length();i++){
            if (Integer.parseInt(String.valueOf(examinedP.charAt(i)))==0){
                substractZeros++;
            }
            else{
                break;
            }
        }

        return examinedP;

    }

    //XOR - Exclusive OR
    public int CRC_substract(int a, int b){

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