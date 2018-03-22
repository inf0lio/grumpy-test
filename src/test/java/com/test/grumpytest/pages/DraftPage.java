package com.test.grumpytest.pages;

import com.codeborne.selenide.SelenideElement;
import com.test.grumpytest.heplers.AcceptanceHelper;
import com.test.grumpytest.widgets.Post;
import com.test.grumpytest.widgets.Posts;
import io.qameta.allure.Step;

import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.sleep;

public class DraftPage extends AcceptanceHelper {

    private SelenideElement container = $(".edit-post");


    @Step
    public DraftPage image(String imageName) {
        uploadFileToRemoteHost(this.container.find(".edit-post_file"),
                "src/test/java/com/test/grumpytest/data/files/" + imageName);

        return this;
    }

    @Step
    public DraftPage text(String someText) {
        this.container.find("textarea[name='body']").setValue(someText);

        return this;
    }

    @Step
    public DraftPage author(String nickName) {
        this.container.find(".edit-post_author").setValue(nickName);

        return this;
    }

    @Step
    public Post confirm() {
        this.container.find(".edit-post_submit").click();

        return new Posts().getPost(0);
    }
}
