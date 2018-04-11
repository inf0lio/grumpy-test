package com.test.grumpytest.mode.widgets;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.test.grumpytest.mode.pages.DraftPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;

public class Post {

    private final String id;

    public Post(String id) {
        this.id = id;
    }

    private SelenideElement container() {
        return $$(".post").findBy(Condition.attribute("data-id", this.id));
    }

    @Step
    public Post assertImage(boolean exists) {
        this.container().find(".post_img").should(exists? exist : not(exist));

        return this;
    }

    @Step
    public Post assertVideo(boolean exists) {
        this.container().find(".post_video").should(exists? exist : not(exist));

        return this;
    }

    @Step
    public Post assertText(String postText) {
        this.container().find(".post_body>p").shouldHave(text(postText));

        return this;
    }

    @Step
    public Post assertAuthor(String name) {
        this.container().find(".post_author").shouldHave(text(name));

        return this;
    }

    @Step
    public DraftPage edit() {
        this.container().find(".post_meta_edit").click();

        return new DraftPage();
    }

}
