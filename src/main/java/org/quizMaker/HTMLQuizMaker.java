package org.quizMaker;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class HTMLQuizMaker
{

    public static void main( String[] args )
    {


        //--Main Menu--//
        if(mainMenu() == 1)
        {
            quizMenu();
        }
        else {
            System.out.println("Ok, have a nice day!");
        }

        //Perhaps later, add some client-side Javascript to display correct answers, etc

    }

    static int mainMenu(){
        int userInput = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to do?");
        System.out.println("1) Make New Quiz");
        System.out.println("2) Exit");
        userInput = scanner.nextInt();
        return userInput;
    }

    static void quizMenu(){

        int questionCount = 0;
        int answerCount = 0;
        String quizTitle;
        String quizDescription;
        ArrayList<String> qArr = new ArrayList<>();
        ArrayList<String[]> aArr = new ArrayList<>();
        Scanner scan = new Scanner(System.in);


        //--Quiz-Making Menu--//
        System.out.println("Input the title of your new quiz: ");
        quizTitle = scan.nextLine();
        System.out.println("Input a description (if any) of quiz. If none, then press \"enter\": ");
        quizDescription = scan.nextLine();
        System.out.println("How many questions will be in your quiz?");
        questionCount = scan.nextInt();
        System.out.println("How many answers per question?");
        answerCount = scan.nextInt();

        //Let's do some processing
        //Clear buffer
        scan.nextLine();
        //For loop that loops through inputting questions
        for(int i = 0; i < questionCount; i++) {
            System.out.println("Please input Question " + (i+1) + ": ");
            qArr.add(scan.nextLine());
            //Ask for answers
            System.out.println("Please provide " + answerCount + " possible answers for this question.");
            String[] answers = new String[answerCount];

                for(int k = 0; k < answerCount ; k++)
                {
                    answers[k] = scan.nextLine();
                }
                aArr.add(answers);

                /* Add optional flagging system for correct answers later, maybe.
                For now, quiz is just front end that does nothing except make flat HTML file.
                System.out.println("Which answer is correct?");
                Would get input here, then store in an array for answer key.
                */
                }

        System.out.println("Thank you. Making Quiz.");
        System.out.println("Here is what your quiz will look like: ");

        //Echo user's input to user
        System.out.println(quizTitle);
        System.out.println(quizDescription);
        for(int i = 0; i < qArr.size(); i++) {
            System.out.println("Question #" + (i+1) + " " + qArr.get(i));
            for (int k = 0; k < aArr.get(i).length; k++) {
                System.out.println(aArr.get(i)[k]);
            }
        }

        //Make quiz into HTML page
        makeHTML(qArr, aArr, quizDescription);


    }

    static void makeHTML(ArrayList<String> qArr, ArrayList<String[]> aArr, String description){
        //The method which will make the HTML of the quiz form
        //Just some sample data right now before I connect it to the rest
        String quizTitle = "Sample Quiz";
        //Get rid of spaces in the quiz title
        String quizTitleFileParse = quizTitle.replaceAll("\\s","");
        String quizDescription;

        try {
            File quizPage = new File("quizPage2.html");
        }
        catch(Exception e)
        {
            System.out.println("An error happened while making the file.");
            System.out.println(e);
        }

        String q1 = "Question content";
        String a1 = "Answer content";
        int qNum = 1;

        String qContent = "<h1>" + quizTitle + "</h1> \n" + "<p>" + description + "</p> \n";

        //Make String of question content
        for(int i = 0; i < qArr.size(); i++) {
            qContent = qContent.concat( "<h2>Question " + (i+1) + " " + qArr.get(i) + " </h2> \n" +
                    "<fieldset> \n" +
                    "<p>" + qArr.get(i) + "</p> \n" +
                    "<ul class=\"answerList\"> \n");
            for (int k = 0; k < aArr.get(i).length; k++) {
                qContent = qContent.concat("<li><label for=\"q"+ (i+1) + "a" + (k+1)+"\"><input type=\"radio\" name=\"q" +(i+1)+ "\" id=\"q" + (i+1) + "a" + (k+1) + "\" /> " + aArr.get(i)[k] + "</label></li> \n");
            }
            qContent = qContent.concat("</ul> \n" +
                                           "</fieldset>");
        }


        try {
            FileWriter quizWriter = new FileWriter("QuizPage_" + quizTitleFileParse + ".html");


            quizWriter.write("<!DOCTYPE html> \n " +
                    "<html> \n" +
                    "<head> \n" +
                    "<title>" + quizTitle + "</title> \n" +
                    "<link rel=\"stylesheet\" href=\"qStyles.css\"> \n" +
                    "</head> \n" +
                    "<style> \n" +
                    ".answerList { list-style-type: upper-alpha; } \n" +
                    "</style> \n" +
                    "<body> \n" +
                    "<form> \n" +
                   qContent +
                    "</form> \n" +
                    "</body>\n" +
                    "</html>"

            );

            quizWriter.close();
        }
        catch(Exception e)
        {
            System.out.println("An error happened while writing to file.");
            System.out.println(e);
        }


    }

}
