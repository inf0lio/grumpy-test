package com.test.grumpytest.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.test.grumpytest.widgets.Posts;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private SelenideElement newPost = $(".title_new");


    @Step
    public MainPage open() {
        Selenide.open("/");
        this.newPost.shouldNotHave(visible);

        return this;
    }

    @Step
    public MainPage login() {
        Selenide.open("/new");
        Selenide.open("/");
        this.newPost.shouldHave(visible);

        return this;
    }

    @Step
    public DraftPage create() {
        newPost.click();

        return new DraftPage();
    }






}
