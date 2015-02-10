# authorization-qdsl-util-api

Utility API to create the permission checks easly for Querydsl based SQL 
queries.

## Example usage

```
QBook book = QBook.book;
BooleanExpression authrPredicate = 
    authorizationQdslUtil.authorizationPredicate(
        userResourceId, book.resourceId, "read", "edit");

SQLQuery query = new SQLQuery(connection, configuration);

return query.from(book)...where(authrPredicate)...list(...);
```

For more information check the javadoc of the 
__org.everit.osgi.authorization.qdsl.util.AuthorizationQdslUtil__ interface.
