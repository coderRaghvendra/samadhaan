import java.nio.file.*;

/**
 * Created by raghvendra.mishra on 20/02/18.
 */
public class DeployTask {

    public static void main(String[] args) {

        try {
            Path FROM = Paths.get("../../IdeaProjects/samadhaan/build/libs/samadhaan-1.0-SNAPSHOT.war");
            Path TO = Paths.get("../../tomcat/webapps/samadhaan-1.0-SNAPSHOT.war");
            //overwrite existing file, if exists
            CopyOption[] options = new CopyOption[]{
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.COPY_ATTRIBUTES
            };
            Files.copy(FROM, TO, options);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
