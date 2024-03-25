package test.structural;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Tag("structural")
@Tag("TooManyActionsException")
public class StructuralTooManyActionsExceptionTest {

    static String FQCN = "bakery.TooManyActionsException";
    static Field[] fields;
    static Method[] methods;

    @BeforeAll
    public static void setUp() {
        fields = StructuralHelper.getFields(FQCN);
        methods = StructuralHelper.getMethods(FQCN);
    }

    @Test
    public void testClassExists() {
        assertTrue(StructuralHelper.classExists(FQCN));
    }

    @Test
    public void testClassIsPublic() {
        assertTrue(StructuralHelper.classIsPublic(FQCN));
    }

    @Test
    public void testClassImplementsSerializable() {
        assertTrue(StructuralHelper.classIsSerializable(FQCN));
    }

    @Test
    public void testClassIsException() {
        assertTrue(StructuralHelper.classIsException(FQCN));
    }

    @Test
    public void testEmptyConstructorIsPublic() throws ClassNotFoundException, NoSuchMethodException {
        assertTrue(StructuralHelper.ctorIsPublic(FQCN ));
    }

}
