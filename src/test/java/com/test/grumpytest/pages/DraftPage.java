package com.test.grumpytest.pages;

import com.codeborne.selenide.SelenideElement;
import com.test.core.Helpers;
import com.test.grumpytest.heplers.AcceptanceHelper;
import com.test.grumpytest.widgets.Post;
import com.test.grumpytest.widgets.Posts;
import io.qameta.allure.Step;

import java.util.Properties;

import static com.codeborne.selenide.Selenide.$;

public class DraftPage extends AcceptanceHelper {

    private SelenideElement container() {
        return $(".edit-post");
    }

    @Step
    public DraftPage image(String imageName) {
        Properties properties = Helpers.getProperties();
        uploadFileToRemoteHost(this.container().find(".edit-post_file"),
                properties.getProperty("files.folder") + imageName);

        return this;
    }

    @Step
    public DraftPage text(String someText) {
        this.container().find("textarea[name='body']").setValue(someText);

        return this;
    }

    @Step
    public DraftPage author(String nickName) {
        this.container().find(".edit-post_author").setValue(nickName);

        return this;
    }

    @Step
    public Post confirm() {
        this.container().find(".edit-post_submit").click();

        return new Posts().getPost(0);
    }
}
