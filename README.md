# NEWS-FEED
## _Spring Boot application for storing, processing and displaying news_

## Features

- Spring security was used to generate token by which user can or cannot access specific endpoints based on role of user. See `main.java.dev.dani.strong.newsfeed.config.` package.
- Spring scheduler was used in news statistics to generate number of news for each source. See `main.java.dev.dani.strong.newsfeed.config.scheduler` package.

## Libraries
- `Spring Boot 3`
- `Spring Security 6`
- `Spring Data JPA`
- `Spring Validation`
- `PostgreSQL`
- `Lombok`
- `io.jsonwebtoken`


# REST service endpoints:
### Before copy pasting endpoint please double check url from controller classes!
##### Just in case if there is typo in url.


# _Auth endpoints:_
| HTTP_ENDPONT | README | METHOD |  ACCESSED_ROLES |
| ------ | ------ | ------ | ------ |
| Registration | http://localhost:8080/api/v1/auth/registration | POST | ALL |
| Authentication | http://localhost:8080/api/v1/auth/authentication | POST | ALL |

# _News endpoints:_
| HTTP_ENDPONT | README | METHOD |  ACCESSED_ROLES |
| ------ | ------ | ------ | ------ |
| Creation | http://localhost:8080/api/v1/news/new | POST | ADMIN, EDITOR |
| Getting By Id | http://localhost:8080/api/v1/news/{id} | GET | ALL |
| Getting All | http://localhost:8080/api/v1/news/all  | GET | ALL |
| Getting By Source Id | http://localhost:8080/api/v1/news/by-source-id/{id} | GET | ALL |
| Getting By Topic Id | http://localhost:8080/api/v1/news//by-topic-id/{id} | GET | ALL |
| Update By Id | http://localhost:8080/api/v1/news/{id} | PUT | ADMIN, EDITOR |
| Delete By Id | http://localhost:8080/api/v1/news/{id} | DELETE | ADMIN, EDITOR |

# _News source endpoints:_
| HTTP_ENDPONT | README | METHOD |  ACCESSED_ROLES |
| ------ | ------ | ------ | ------ |
| Creation | http://localhost:8080/api/v1/news-source/new | POST | ADMIN |
| Getting By Id | http://localhost:8080/api/v1/news-source/{id} | GET | ADMIN |
| Getting By SourceName | http://localhost:8080/api/v1/news-source/source/{sourceName}  | GET | ADMIN |
| Getting All | http://localhost:8080/api/v1/news-source/all | GET | ADMIN |
| Update By Id | http://localhost:8080/api/v1/news-source/{id} | PUT | ADMIN |
| Delete By Id | http://localhost:8080/api/v1/news-source/{id} | DELETE | ADMIN |

# _Role endpoints:_
| HTTP_ENDPONT | README | METHOD |  ACCESSED_ROLES |
| ------ | ------ | ------ | ------ |
| Creation | http://localhost:8080/api/v1/role/new | POST | ADMIN |
| Getting By Id | http://localhost:8080/api/v1/role/{id} | GET | ADMIN |
| Getting By RoleName | http://localhost:8080/api/v1/role?role={roleName}  | GET | ADMIN |
| Getting All | http://localhost:8080/api/v1/role/all | GET | ADMIN |
| Update By Id | http://localhost:8080/api/v1/role/{id} | PUT | ADMIN |
| Delete By Id | http://localhost:8080/api/v1/role/{id} | DELETE | ADMIN |

# _Topic endpoints:_
| HTTP_ENDPONT | README | METHOD |  ACCESSED_ROLES |
| ------ | ------ | ------ | ------ |
| Creation | http://localhost:8080/api/v1/topic/new | POST | ADMIN |
| Getting By Id | http://localhost:8080/api/v1/topic/{id} | GET | ADMIN |
| Getting By TopicName | http://localhost:8080/api/v1/topic/{topic} | GET | ADMIN |
| Getting All | http://localhost:8080/api/v1/topic/all | GET | ADMIN |
| Update By Id | http://localhost:8080/api/v1/topic/{id} | PUT | ADMIN |
| Delete By Id | http://localhost:8080/api/v1/topic/{id} | DELETE | ADMIN |

# _User endpoints:_
| HTTP_ENDPONT | README | METHOD |  ACCESSED_ROLES |
| ------ | ------ | ------ | ------ |
| Creation | http://localhost:8080/api/v1/user/new | POST | ADMIN |
| Getting By Id | http://localhost:8080/api/v1/user/{id} | GET | ADMIN |
| Getting By Username | http://localhost:8080/api/v1/user?username={username}  | GET | ADMIN |
| Getting By RoleName | http://localhost:8080/api/v1/user/role?role={roleName}  | GET | ADMIN |
| Getting All | http://localhost:8080/api/v1/user/all | GET | ADMIN |


