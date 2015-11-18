/*
 * Copyright (C) 2011 Everit Kft. (http://www.everit.biz)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.everit.authorization.qdsl.util;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;

/**
 * Utility to Authorization Querydsl.
 */
public interface AuthorizationQdslUtil {

  /**
   * Creates a predicate for a Querydsl based SQL query. If the predicate is appended to the where
   * part of the main query, the results will be filtered based on authorization.<br>
   * <br>
   * E.g.: Users and books are resources. Users can view and edit books. The authorized resource id
   * is the resourceId of the user that is currently authenticated by the system. To list the books
   * that the currently authenticated user can view or edit, the following code snippet should be
   * used:
   *
   * <pre>
   * QBook book = QBook.book;
   * BooleanExpression authrPredicate = authorizationQdslUtil.authorizationPredicate(userResourceId, book.resourceId, "read", "edit"); // CS_DISABLE_LINE_LENGTH
   *
   * SQLQuery query = new SQLQuery(connection, configuration);
   *
   * return query.from(book)...where(authrPredicate)...list(...);
   * </pre>
   *
   * @param authorizedResourceId
   *          The id of the resource that should be authorized. The predicate will provide true if
   *          the passed resource or any of its parent has rights to do any of the actions on the
   *          provided target resourceId expression.
   * @param targetResourceId
   *          Expression that provides the resource that the permission is defined on. This
   * @param actions
   *          If the authorized resource has permission to do any of the actions on the target
   *          resource record (directly or by inheritance), the result will provide
   *          <code>true</code>.
   * @return An expression that can be used as a predicate in the main query.
   * @throws NullPointerException
   *           if the targetResourceId parameter is null, the actions parameter is null or the
   *           actions array contains null value.
   * @throws IllegalArgumentException
   *           if the actions parameter is a zero length array.
   */
  BooleanExpression authorizationPredicate(long authorizedResourceId,
      Expression<Long> targetResourceId, String... actions);

}
