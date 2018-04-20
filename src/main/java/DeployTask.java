import java.io.File;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by raghvendra.mishra on 20/02/18.
 */
public class DeployTask {

    public static void main(String[] args) {

        System.out.println(isWordClose("hit", "hot"));
        List<String> list = new ArrayList<>();
        list.add("hot");
        list.add("dot");
        list.add("dog");
        list.add("lot");
        list.add("log");
        list.add("cog");
        System.out.println(findLadders("hit", "cog", list));

//        String obj = "2018-03-26 09:00:00.000";
//        System.out.println(obj);
//        if (obj.substring(obj.length() - 4, obj.length()).equals(".000")) {
//            obj = obj.substring(0, obj.length()-4).concat(" AM");
//        }
//        System.out.println("string=" + obj + "PP");
//        System.out.println(obj.contains("-") ?
//                DateUtility.parseDateTime(obj, "[yyyy][yy]-M-d HH:mm:ss"):
//                DateUtility.parseDateTime(obj, "M/d/[yyyy][yy] h:mm:ss a"););

//        try {
//            Path FROM = Paths.get("../../IdeaProjects/samadhaan/build/libs/samadhaan-1.0-SNAPSHOT.war");
//            Path TO = Paths.get("../../tomcat/webapps/samadhaan-1.0-SNAPSHOT.war");
//            //overwrite existing file, if exists
//            CopyOption[] options = new CopyOption[]{
//                    StandardCopyOption.REPLACE_EXISTING,
//                    StandardCopyOption.COPY_ATTRIBUTES
//            };
//            Files.copy(FROM, TO, options);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        System.out.println("begin word = " + beginWord);
        System.out.println("end word = " + endWord);
        System.out.println("word list = " + wordList);
        System.out.println("\n\n\n");
        if(wordList.size() == 1){
            if(isWordClose(beginWord, wordList.get(0))){
                List<List<String>> strList = new ArrayList<>();
                List<String> list = new ArrayList<String>() {{
                    add(beginWord);
                    add(wordList.get(0));
                }};
                strList.add(list);
                System.out.println("returing basecase " + strList);
                return strList;
            }
            else
                return null;
        }
        //prepare close word list
        wordList.remove(endWord);
        System.out.println("word list after removal of end word : " + wordList);
        List<String> closeWordList = new ArrayList<>();
        List<String> wordListCopy = new ArrayList<>(wordList);
        for(String w : wordListCopy){
            System.out.println(w + " " + endWord + " " + isWordClose(w, endWord));
            if(isWordClose(w, endWord)){
                closeWordList.add(w);
                wordList.remove(w);
            }
        }
        System.out.println("close word list : " + closeWordList);
        List<List<String>> sol = new ArrayList<>();
        //find solution recursively
        for(String w : closeWordList){
            wordList.add(w);
            List<List<String>> tempSol = findLadders(beginWord, w, wordList);
            if(tempSol != null && !tempSol.isEmpty()){
                System.out.println("adding to sol; tempsol = " + tempSol);
                sol.addAll(addTwoList(tempSol, endWord));
            }
            wordList.remove(w);
        }
        wordList.addAll(closeWordList);
        wordList.add(endWord);
        return sol;
    }

    public static List<List<String>> addTwoList(List<List<String>> list, String word){
        if(list == null || list.isEmpty())
            return null;
        for(List<String> sl : list){
            sl.add(word);
        }
        return list;
    }

    public static boolean isWordClose(String a, String b){
        if((a.length() != b.length()) || a.equals(b))
            return false;
        char[] aArray = a.toCharArray();
        char[] bArray = b.toCharArray();
        int i = 0;
        for( ; i < aArray.length; i++ ){
//            System.out.println("A : " + aArray[i]);
//            System.out.println("B : " + bArray[i]);
            if(aArray[i] != bArray[i])
                break;
        }
//        System.out.println("i = " + i);
        if(i < aArray.length){
            i++;
//            System.out.println("i = " + i);

            for( ; i < aArray.length; i++ ){
                if(aArray[i] != bArray[i])
                    return false;
            }
            return true;
        } else {
            return false;
        }
    }
}
