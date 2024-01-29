package com.utils;

import java.security.SecureRandom;

public class KuroEncrypterTool {

    public String loadHexToString(String input) { //input mus be encrypted text before encrypted with this tool
        String inputCutFirstPart = ""; //Store first cut
        String inputCutSecondPart = ""; //Store second cut
        StringBuffer inputBuffered = new StringBuffer(input); //buffered input
        String inputConvertToString = ""; //store final String

        inputBuffered.reverse().toString();  //reverse input
        inputConvertToString = this.convertHexToString(inputBuffered.toString()); //convert hexadecimal input to string (still be hexa because need a second convert)

        //check lenght of input if no pair
        if (inputConvertToString.length() % 2 == 0) {
            inputCutFirstPart = inputConvertToString.substring(0, inputConvertToString.length() / 2); //cut input
            inputCutSecondPart = inputConvertToString.substring(inputConvertToString.length() / 2, inputConvertToString.length()); //second cut
        } else {
            inputCutFirstPart = inputConvertToString.substring(0, inputConvertToString.length() / 2); //cut input
            inputCutSecondPart = inputConvertToString.substring((inputConvertToString.length() / 2) - 1, inputConvertToString.length()); //second cut
        }

        return this.convertHexToString(inputCutSecondPart + inputCutFirstPart); //join inputs and convert again from hex to string
    }

    public String saveTextToHex(String input) { //input must be normal text
        String inputCutFirstPart = ""; //store first cut
        String inputCutSecondPart = ""; //store second cut
        String secondInputBuffered = ""; //join parts
        StringBuffer secondHexInputContainer = new StringBuffer(); //store final hex

        inputCutFirstPart = this.convertStringToHex(input).substring(0, this.convertStringToHex(input).length() / 2); // cut input
        inputCutSecondPart = this.convertStringToHex(input).substring(this.convertStringToHex(input).length() / 2, this.convertStringToHex(input).length()); //second cut
        secondInputBuffered = inputCutSecondPart + inputCutFirstPart; //Store input in a second input buffered (turn second cut first and second to first)

        secondHexInputContainer.append(this.convertStringToHex(secondInputBuffered)); //String to hex again and store on hex container
        secondHexInputContainer.reverse(); // reverse file text

        return secondHexInputContainer.toString(); //return text
    }

    private String convertHexToString(String hex) {

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for (int i = 0; i < hex.length() - 1; i += 2) {

            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char) decimal);

            temp.append(decimal);
        }

        return sb.toString();
    }

    private String convertStringToHex(String text) {

        StringBuffer inputBuffered = new StringBuffer(text); //buffered input
        char[] charzedInput = (new StringBuffer(inputBuffered).toString()).toCharArray(); //cut input to char
        StringBuffer hexInputContainer = new StringBuffer(); //declare hexBuffered container
        for (int i = 0; i < charzedInput.length; i++) { // String to hex and store on hex container
            hexInputContainer.append(Integer.toHexString((int) charzedInput[i]));
        }
        return hexInputContainer.toString();
    }

    private String randomString(int len) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }
}