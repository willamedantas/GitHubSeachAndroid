package willamedantas.inec.mobile.perfilgithub.util;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by willa on 29/12/2017.
 */
public class StringUtilTest {

    @Test
    public void deveFormatarData(){
        String dataOld = "2017-08-18T02:49:23Z";
        String data = StringUtil.dataFormat(dataOld);
        assertEquals("18/08/2017", data);
    }

    @Test
    public void naoDeveFormatarData(){
        String dataOld = "";
        String data = StringUtil.dataFormat(dataOld);
        assertNull(data);
    }
}
