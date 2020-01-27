import graphs.AnswerService;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        File inputFile = new File("input.txt");
        File file = new File("output.txt");

        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        String st = br.readLine();
        String[] routes = st.split(", ");

        AnswerService answerService = new AnswerService(routes);

        writer.write("Output #1: "  + answerService.firstAnswer()   + "\n");
        writer.write("Output #2: "  + answerService.secondAnswer()  + "\n");
        writer.write("Output #3: "  + answerService.thirdAnswer()   + "\n");
        writer.write("Output #4: "  + answerService.fourthAnswer()  + "\n");
        writer.write("Output #5: "  + answerService.fifthAnswer()   + "\n");
        writer.write("Output #6: "  + answerService.sixthAnswer()   + "\n");
        writer.write("Output #7: "  + answerService.seventhAnswer() + "\n");
        writer.write("Output #8: "  + answerService.eightsAnswer()  + "\n");
        writer.write("Output #9: "  + answerService.ninesAnswer()   + "\n");
        writer.write("Output #10: " + answerService.tensAnswer()    + "\n");

        writer.close();
    }


}
