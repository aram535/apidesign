package impl;

import api.classes.Compute;
import api.classes.Support;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Impl {
    public static void main(String[] args) {
        class ProviderWrittenAgainstVersion1 extends Compute {
            @Override
            public List<String> getData() {
                return Collections.singletonList("Hello");
            }
        }
        int index1 = Support.searchByDescription("Hello", new ProviderWrittenAgainstVersion1());
        assert index1 == 0;
        int index2 = Support.searchByDescription("Unknown", new ProviderWrittenAgainstVersion1());
        assert index2 == -1;

        
        class ProviderWrittenAgainstVersion2 extends Compute {
            @Override
            public List<String> getData() {
                return Collections.singletonList("Hello");
            }

            @Override
            public Map<String, String> getDataAndDescription() {
                return Collections.singletonMap("Hello", "Says hello");
            }
        }

        int index3 = Support.searchByDescription("Says hello", new ProviderWrittenAgainstVersion2());
        assert index3 == 0;
    }
}
