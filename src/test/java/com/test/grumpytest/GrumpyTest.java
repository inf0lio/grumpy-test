package com.test.grumpytest;

import com.github.javafaker.Faker;
import com.test.grumpytest.categories.EndToEnd;
import com.test.grumpytest.configs.BaseTest;
import com.test.grumpytest.pages.MainPage;
import com.test.grumpytest.pages.DraftPage;
import com.test.grumpytest.widgets.Post;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.test.core.Gherkin.*;

public class GrumpyTest extends BaseTest {

    public GrumpyTest(String browserName, String browserVersion, String gridUrl, String platform) {
        super(browserName, browserVersion, gridUrl, platform);
    }

    private Faker faker = new Faker();

    @Test
    @Category(EndToEnd.class)
    public void createReadUpdatePost() {
        GIVEN("Open the app and login");
        MainPage mainPage = new MainPage();
        mainPage.open().login();

        GIVEN("Page to creation of post");
        DraftPage draft = mainPage.create();

        String text_1 = '"' + faker.sentence() + '"' + " - " + faker.name();
        String user_1 = "igrishaev";

        AND("Create a new post");
        Post post = draft.text(text_1)
                         .author(user_1)
                         .confirm();


        EXPECT("The post created");
        post.assertImage(false)
            .assertText(text_1)
            .assertAuthor(user_1);

        String text_2 = '"' + faker.sentence() + '"' + " - " + faker.name();
        String user_2 = "freetonik";

        THEN("Edit the post");
        post.edit()
            .image("pic_1.jpeg")
            .text(text_2)
            .author(user_2)
            .confirm();

        EXPECT("The post edited");
        post.assertImage(true)
                .assertText(text_2)
                .assertAuthor(user_2);
    }
}
