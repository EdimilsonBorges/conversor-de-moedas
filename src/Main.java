import service.ApiService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ApiService apiService = new ApiService();
        System.out.println(apiService.toConvert("USD","BRL",100));
    }
}