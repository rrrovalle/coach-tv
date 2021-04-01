package udesc.pin3;

import io.quarkus.test.junit.NativeImageTest;
import udesc.pin3.User.UserResourceTest;

@NativeImageTest
public class NativeUserResourceIT extends UserResourceTest {

    // Execute the same tests but in native mode.
}