
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class test {

    @Test
    public void authorize() {
        String resKeys = "admin_order_query";
        String operKeys = "AUDIT";
        int staffId = -999;
        List<Map<String, Boolean>> results = new ArrayList();
//        Set<String> userOperationList = this.getResourceKeyAndOperationKey(staffId);

            Map<String, Boolean> result = new HashMap();
//            if (userOperationList.contains(resKeys[i] + "-" + operKeys[i])) {
//                result.put("result", true);
//            } else {
//                result.put("result", false);
//            }
            result.put("result",true);
        System.out.println(result);
            results.add(result);
        boolean a = authorize(staffId,resKeys,operKeys);

        System.out.println(a);

    }

     boolean authorize(int staffId, String resKey, String operKey) {
        Set<String> userOperationList = new HashSet<>();
//        userOperationList.add(resKey+"-"+operKey);
        return userOperationList.contains(resKey + "-" + operKey);
    }

    @Test
    public void test2(){
        Map<String, String> map = new HashMap<>();
        List<Map<String, String>> list = new ArrayList<>();
        map.put("k1","v1");
        list.add(map);
    }
}
