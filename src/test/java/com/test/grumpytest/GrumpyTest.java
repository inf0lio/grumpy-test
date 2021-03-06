package com.test.grumpytest;

import com.github.javafaker.Faker;
import com.test.grumpytest.categories.Acceptance;
import com.test.grumpytest.categories.EndToEnd;
import com.test.grumpytest.configs.BaseTest;
import com.test.grumpytest.mode.pages.MainPage;
import com.test.grumpytest.mode.pages.DraftPage;
import com.test.grumpytest.mode.widgets.Post;
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

        AND("Create a new post for Vlad");
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
            .file("pic_1.jpeg")
            .text(text_2)
            .author(user_2)
            .confirm();

        EXPECT("The post edited");
        post.assertImage(true)
                .assertText(text_2)
                .assertAuthor(user_2);
    }

    @Test
    @Category(Acceptance.class)
    public void addVideoToPost() {
        MainPage mainPage = new MainPage().open().login();
        DraftPage draft = mainPage.create();
        Post post = draft.file("video_1.mp4").confirm();

        post.assertVideo(true);
    }
}
