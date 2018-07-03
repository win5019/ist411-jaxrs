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

package edu.psu.ist411.presentation;

import edu.psu.ist411.presentation.UserModels.UserView;

import org.springframework.data.domain.Page;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.stream.Collectors;

@Provider
@Produces(MediaType.TEXT_HTML)
public class UserViewPageMessageBodyWriter implements MessageBodyWriter<Page<UserView>> {
    @Override
    public boolean isWriteable(
        final Class<?> type,
        final Type genericType,
        final Annotation[] annotations,
        final MediaType mediaType
    ) {
        return Page.class.isAssignableFrom(type);
    }

    @Override
    public void writeTo(
        final Page<UserModels.UserView> page,
        final Class<?> type,
        final Type genericType,
        final Annotation[] annotations,
        final MediaType mediaType,
        final MultivaluedMap<String, Object> httpHeaders,
        final OutputStream out
    ) throws
        IOException,
        WebApplicationException
    {
        try (final Writer writer = new PrintWriter(out)) {
            final String markup = String.join("\r\n", new String[] {
                "<!DOCTYPE html>",
                "<html dir=\"ltr\" lang=\"en\">",
                "    <head>",
                "        <meta charset=\"utf-8\"/>",
                "        <title>Users</title>",
                "        <link rel=\"stylesheet\" href=\"//stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css\" integrity=\"sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB\" crossorigin=\"anonymous\"/>",
                "        <link rel=\"stylesheet\" href=\"/css/main.css\"/>",
                "    </head>",
                "    <body>",
                "        <article class=\"users\">",
                "            <section class=\"createUser\">",
                "                <h2>Create user</h2>",
                "                <form id=\"createUserForm\">",
                "                    <label>Email: <input type=\"email\" name=\"email\" value=\"\"/></label><br/>",
                "                    <label>First name: <input type=\"text\" name=\"firstName\" value=\"\"/></label><br/>",
                "                    <label>Last name: <input type=\"text\" name=\"lastName\" value=\"\"/></label><br/>",
                "                    <input type=\"submit\" value=\"Submit\"/>",
                "                </form>",
                "            </section>",
                "",
                "            <section class=\"updateUser\">",
                "                <h2>Update user</h2>",
                "                <form id=\"updateUserForm\">",
                "                    <label>ID: <input type=\"number\" name=\"id\" value=\"\" autocomplete=\"off\"/></label><br/>",
                "                    <label>Email: <input type=\"email\" name=\"email\" value=\"\"/></label><br/>",
                "                    <label>First name: <input type=\"text\" name=\"firstName\" value=\"\"/></label><br/>",
                "                    <label>Last name: <input type=\"text\" name=\"lastName\" value=\"\"/></label><br/>",
                "                    <input type=\"submit\" value=\"Submit\"/>",
                "                </form>",
                "            </section>",
                "",
                "            <section class=\"users\">",
                "                <h2>Users</h2>",
                "                <p>" + page.toString() + "</p>",
                "                <pre>" + page.getContent().stream().map(UserModels.UserView::toString).collect(Collectors.joining("\r\n")) + "</pre>",
                "            </section>",
                "        </article>",
                "",
                "        <script src=\"//code.jquery.com/jquery-3.3.1.min.js\"></script>",
                "        <script src=\"/js/main.js\"></script>",
                "    </body>",
                "</html>",
            });

            writer.write(markup);
            writer.flush();
        }
    }
}