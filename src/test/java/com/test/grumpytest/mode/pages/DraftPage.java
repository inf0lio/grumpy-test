package com.test.grumpytest.mode.pages;

import com.codeborne.selenide.SelenideElement;
import com.test.core.Helpers;
import com.test.core.AcceptanceHelper;
import com.test.grumpytest.mode.widgets.Post;
import com.test.grumpytest.mode.widgets.Posts;
import io.qameta.allure.Step;

import java.util.Properties;

import static com.codeborne.selenide.Selenide.$;

public class DraftPage extends AcceptanceHelper {

    private SelenideElement container() {
        return $(".edit-post");
    }

    @Step
    public DraftPage file(String imageName) {
        Properties properties = Helpers.getProperties();
        uploadFileToRemoteHost(this.container().find(".edit-post_file"),
                properties.getProperty("files.path") + imageName);

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

        return new Posts().get(0);
    }

    @Step
    public Posts cancel() {
        this.container().find(".edit-post_cancel").click();

        return new Posts();
    }
}
