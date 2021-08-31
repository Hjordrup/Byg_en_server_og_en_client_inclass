package Time;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeServer extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        TextArea taLog = new TextArea();

        // Create a scene and place it in the stage
        Scene scene = new Scene(new ScrollPane(taLog), 450, 200);
        primaryStage.setTitle("Time Server"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage


        new Thread(() ->{
            try{
                ServerSocket serverSocket = new ServerSocket(8080);
                Platform.runLater(() -> taLog.appendText(new Date() +
                        ": Server started at socket 8080\n"));


                while(true){
                    Socket socket = serverSocket.accept();
                    // Create data input and output streams
                    DataInputStream inputFromClient = new DataInputStream(
                            socket.getInputStream());
                    DataOutputStream outputToClient = new DataOutputStream(
                            socket.getOutputStream());
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String time = dtf.format(now);

                    outputToClient.writeUTF(time);
                    outputToClient.flush();

                    Platform.runLater(() -> taLog.appendText("The time is " + time + '\n'));
                }

            }catch (IOException exception){
                exception.printStackTrace();
            }
        }).start();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
