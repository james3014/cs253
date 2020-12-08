import service.InterfaceService;
import service.InterfaceServiceImpl;

public class Main {

    public static void main(String[] args) {

        InterfaceService interfaceService = new InterfaceServiceImpl();
        interfaceService.menu();
    }
}
