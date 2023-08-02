import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class McAvailable {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Yo, enter a Minecraft name and let's see if it's up for grabs: ");
            String username = reader.readLine();

            boolean isAvailable = checkUsernameAvailability(username);

            if (isAvailable) {
                System.out.println("You're in luck! The name " + username + " is available!");
            } else {
                System.out.println("Bummer, " + username + " is already taken. Keep brainstorming!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkUsernameAvailability(String username) throws IOException {
        URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + username);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        return responseCode == HttpURLConnection.HTTP_NOT_FOUND;
    }
}
