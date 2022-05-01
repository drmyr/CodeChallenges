package trees;

import org.junit.jupiter.api.Test;

import static trees.PhoneNumberMnemonics.generateMnemonicsImperative;

class PhoneNumberMnemonicsTest {

    @Test
    void generateMnemonicsTest() {
        generateMnemonicsImperative("1209").forEach(System.out::println);
    }
}