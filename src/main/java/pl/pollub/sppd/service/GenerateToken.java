package pl.pollub.sppd.service;

public class GenerateToken {

    private static final String template = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String randomGenerator(int count){

        StringBuilder builder = new StringBuilder();

        for(int i=0; i<count; i++){

            int number = (int)(Math.random()*template.length());
            builder.append(template.charAt(number));
        }
        return builder.toString();
    }
}
