/*
 * Copyright 2018 Group 5.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*jslint devel: true, browser: true, long: true */
/*global $ */
(function () {
    "use strict";

    function formatJson(object) {
        return JSON.stringify(object, null, 4);
    }

    function getFormData(form) {
        const object = {};
        $.map(form.serializeArray(), function (input) {
            object[input.name] = input.value;
        });
        return object;
    }

    const createUserForm = $("#createUserForm");
    createUserForm.on("submit", function (event) {
        event.preventDefault();
        $.ajax({
            type: "POST",
            url: "/api/users",
            cache: false,
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(getFormData(createUserForm)),
            dataType: "json"
        }).done(function (response) {
            alert("Done!: " + formatJson(response));
        }).fail(function (request) {
            const object = JSON.parse(request.responseText);
            alert("Failed!: " + formatJson(object));
        });
    });

    const updateUserForm = $("#updateUserForm");
    updateUserForm.on("submit", function (event) {
        event.preventDefault();
        const data = getFormData(updateUserForm);
        $.ajax({
            type: "PUT",
            url: "/api/users/" + encodeURIComponent(data.id),
            cache: false,
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            dataType: "json"
        }).done(function (response) {
            alert("Done!: " + formatJson(response));
        }).fail(function (request) {
            const object = JSON.parse(request.responseText);
            alert("Failed!: " + formatJson(object));
        });
    });

    const updateIdInput = updateUserForm.find("[name=\"id\"]");
    const updateEmailInput = updateUserForm.find("[name=\"email\"]");
    const updateFirstNameInput = updateUserForm.find("[name=\"firstName\"]");
    const updateLastNameInput = updateUserForm.find("[name=\"lastName\"]");
    updateIdInput.focusout(function () {
        const data = getFormData(updateUserForm);
        $.ajax({
            type: "GET",
            url: "/api/users/" + encodeURIComponent(data.id),
            cache: false,
            contentType: "application/json; charset=utf-8"
        }).done(function (response) {
            updateEmailInput.val(response.email);
            updateFirstNameInput.val(response.firstName);
            updateLastNameInput.val(response.lastName);
        }).fail(function (request) {
            const object = JSON.parse(request.responseText);
            alert("Failed!: " + formatJson(object));
        });
    });
}());