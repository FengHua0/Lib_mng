# 格式



| code | description                                    |
| ---- | ---------------------------------------------- |
| 1**  | 信息，服务器收到请求，需要请求者继续执行操作   |
| 2**  | 成功，操作被成功接收并处理                     |
| 3**  | 重定向，需要进一步的操作以完成请求             |
| 4**  | 客户端错误，请求包含语法错误或无法完成请求     |
| 5**  | 服务器错误，服务器在处理请求的过程中发生了错误 |



```json
{
    "code": 200,
    "message": "Success",
    "data": {
    }
}
```



```json
{
    "code": 404,
    "message": "Fail: not found",
    "data": null
}
```



```json
{
    "code": 400,
    "message": "Fail: bad request",
    "data":null
}
```





# 登录

**接口地址**



| 方法 | 地址   |
| ---- | ------ |
| POST | /login |



**请求体参数**

| 参数     | 类型   | 是否必需 | 描述         |
| -------- | ------ | -------- | ------------ |
| username | string | 是       | 用户名或邮箱 |
| password | string | 是       | 密码         |



```json
{
	"username":"test1",
	"password":"1234",
}
```





**响应参数**

| 参数       | 类型   | 描述                                                   |
| ---------- | ------ | ------------------------------------------------------ |
| code       | int    | 状态码                                                 |
| message    | string | 提示信息                                               |
| data       | array  | 数据                                                   |
| data.token | string | 验证的token，前端应将其保存，后面每次发起请求携带token |



```json
{
	"code": 200,
	"message": "Success: post /login",
	"data": {
		"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
	}
}

```





```json
{
	"code":400,
	"message":"Fail: invalid username or password",
	"data":null
}
```



# 注册

**接口地址**




| 方法 | 地址      |
| ---- | --------- |
| POST | /register |



**请求体参数**

| 参数     | 类型   | 是否必需 | 描述                        |
| -------- | ------ | -------- | --------------------------- |
| username | string | 是       | 用户名                      |
| password | string | 是       | 密码                        |
| email    | string | 是       | 邮箱                        |
| userRole | string | 是       | 用户角色，{"user", "admin"} |



```json
{
    "username":"tester",
    "password":"123456",
    "email":"tester@qq.com",
    "userRole":"user"
}
```





**响应参数**

| 参数    | 类型   | 描述     |
| ------- | ------ | -------- |
| code    | int    | 状态码   |
| message | string | 提示信息 |
| data    | array  | 数据     |



```json
{
	"code": 200,
	"message" : "Success: post /register",
	"data": null
}
```



```json
{
	"code": 400,
	"message": "Fail: duplicate username",
	"data": null
}
```



```json
{
	"code": 400,
	"message": "Fail: duplicate email",
	"data": null
}
```





```json
{
    "code": 400,
    "message": "Fail: data too long",
    "data":null
}
```



# 管理员



## 个人中心信息显示

**接口地址**



| 方法 | 地址          |
| ---- | ------------- |
| GET  | /user/profile |



**请求体参数**



无



**响应参数**



| 参数     | 类型   | 描述                     |
| -------- | ------ | ------------------------ |
| userId   | int    | 用户ID                   |
| username | string | 用户名                   |
| email    | string | 邮箱地址                 |
| avatar   | blob   | 头像图片数据，base64编码 |
| userRole | string | 用户角色                 |
| money    | int    | 用户余额                 |



```json
{
    "code": 200,
    "message": "Success: get /user/profile",
    "data": {
        "userId": 1,
        "username": "jia",
        "password": "1234",
        "email": "123456789@qq.com",
        "avatar": null,
        "borrowPerms": 1,
        "userRole": "admin",
        "money": 100
    }
}
```



```json
{
    "code": 400,
    "message": "Fail: invalid token",
    "data":null
}
```



```json
{
    "code": 400,
    "message": "Fail: token parse error",
    "data":null
}
```



```json
{
    "code": 400,
    "message": "Fail: no such user",
    "data":null
}
```



## 修改个人信息

**接口地址**



| 方法 | 地址          |
| ---- | ------------- |
| PUT  | /user/profile |



**请求体参数**



| 参数     | 类型   | 是否必需 | 描述                         |
| -------- | ------ | -------- | ---------------------------- |
| email    | string | 否       | 新的邮箱地址                 |
| password | string | 否       | 新的密码                     |
| avatar   | blob   | 否       | 新的头像图片数据，base64编码 |

修改哪些参数就带哪些参数



```json
{
    "email": "newemail@example.com",
    "password": "newpassword123",
    "avatar": "/9j/4AAQSkZJRgABAQEAYABgAAD/..."
}
```



**响应参数**



| 参数    | 类型   | 描述     |
| ------- | ------ | -------- |
| code    | int    | 状态码   |
| message | string | 提示信息 |



```json
{
    "code": 200,
    "message": "Success:put /user/profile",
    "data":null
}
```



```json
{
    "code": 400,
    "message": "Fail: bad request",
    "data":null
}
```



## 图书信息目录

**接口地址**



| 方法 | 地址               |
| ---- |------------------|
| GET  | /user/books/info |



**请求体参数**

无



**响应参数**

| 参数                | 类型   | 描述         |
| ------------------- | ------ | ------------ |
| code                | int    | 状态码       |
| message             | string | 提示信息     |
| data                | array  | 图书目录数组 |
| data[i].isbn        | string | ISBN号       |
| data[i].title       | string | 标题         |
| data[i].author      | string | 作者         |
| data[i].cover       | blob   | 封面图片数据 |
| data[i].description | string | 描述         |
| data[i].available   | int    | 现存数量     |
| data[i].borrowed    | int    | 被借阅数量   |



```json
{
    "code": 200,
    "message": "Success: get /user/books/info",
    "data": [
        {
            "isbn": "978-3-16-148410-0",
            "title": "Book Title 1",
            "author": "Author 1",
            "cover": "/9j/4AAQSkZJRgABAQEAYABgAAD/...",
            "description": "Description of Book 1",
            "location": "B333",
            "available": 5,
            "borrowed": 10
        },
        {
            "isbn": "978-3-16-148410-1",
            "title": "Book Title 2",
            "author": "Author 2",
            "cover": "/9j/4AAQSkZJRgABAQEAYABgAAD/...",
            "description": "Description of Book 2",
            "location": "B333",
            "available": 3,
            "borrowed": 8
        }
    ]
}

```



## 图书信息检索

**接口地址**



| 方法 | 地址               |
| ---- | ------------------ |
| GET  | /user/books/search |



**请求参数**

| 参数    | 类型   | 是否必需 | 描述                                   |
| ------- | ------ | -------- | -------------------------------------- |
| method  | string | 是       | 通过哪种方式 {"title","author","isbn"} |
| keyword | string | 是       | 检索关键词                             |





**Query Params**



```
/user/books/search?method=title&keyword=SPM
```



```
/user/books/search?method=author&keyword=WenhuiWei
```



```
/user/books/search?method=isbn&keyword=11223344
```





**响应参数**



| 参数                | 类型   | 描述         |
| ------------------- | ------ | ------------ |
| code                | int    | 状态码       |
| message             | string | 提示信息     |
| data                | array  | 检索结果数组 |
| data[i].isbn        | string | ISBN号       |
| data[i].title       | string | 标题         |
| data[i].author      | string | 作者         |
| data[i].cover       | blob   | 封面图片数据 |
| data[i].description | string | 描述         |
| data[i].available   | int    | 现存数量     |
| data[i].borrowed    | int    | 被借阅数量   |



```json
{
    "code": 200,
    "message": "Success: get /user/books/search",
    "data": [
        {
            "isbn": "978-3-16-148410-0",
            "title": "Book Title 1",
            "author": "Author 1",
            "cover": "/9j/4AAQSkZJRgABAQEAYABgAAD/...",
            "description": "Description of Book 1",
            "location": "B333",
            "available": 5,
            "borrowed": 10
        },
        {
            "isbn": "978-3-16-148410-1",
            "title": "Book Title 2",
            "author": "Author 2",
            "cover": "/9j/4AAQSkZJRgABAQEAYABgAAD/...",
            "description": "Description of Book 2",
            "location": "B333",
            "available": 3,
            "borrowed": 8
        }
    ]
}

```





## 图书实体列表

**接口地址**



| 方法 | 地址                              |
| ---- | --------------------------------- |
| GET  | /admin/books/instance-list/{isbn} |



**请求体参数**

无



**响应参数**

| 参数                 | 类型     | 描述         |
| -------------------- | -------- | ------------ |
| code                 | int      | 状态码       |
| message              | string   | 提示信息     |
| data                 | array    | 图书实体数组 |
| data[i].instanceId   | int      | 图书实体ID   |
| data[i].isbn         | string   | 图书ISBN     |
| data[i].borrowStatus | int      | 图书借阅状态 |
| data[i].addTime      | datetime | 图书添加时间 |

```json
{
    "code": 200,
    "message": "Success: get /admin/books/instance-list/{isbn}",
    "data": [
        {
            "instanceId": 1,
            "isbn": "978-3-16-148410-0",
            "borrowStatus": 1,
            "addTime": "2024-04-08T11:19:08"
        },
        {
            "instanceId": 2,
            "isbn": "978-3-16-148410-0",
            "borrowStatus": 1,
            "addTime": null
        },
        {
            "instanceId": 3,
            "isbn": "978-3-16-148410-0",
            "borrowStatus": 1,
            "addTime": null
        }
    ]
}
```



## 图书借阅信息目录

**接口地址**



| 方法 | 地址                        |
| ---- | --------------------------- |
| GET  | /admin/books/borrowing-info |



**请求参数**

| 参数 | 类型   | 是否必需 | 描述     |
| ---- | ------ | -------- | -------- |
| isbn | string | 是       | 图书ISBN |



**Query Params**



```
/admin/books/borrowing-info?isbn=978-3-16-148410-0
```





**响应参数**

| 参数               | 类型   | 描述                 |
| ------------------ | ------ | -------------------- |
| code               | int    | 状态码               |
| message            | string | 提示信息             |
| data               | array  | 图书借阅信息目录数组 |
| data[i].username   | string | 读者用户名           |
| data[i].borrowDate | string | 借阅日期             |
| data[i].dueDate    | string | 到期日期             |
| data[i].bookId     | int    | 借阅书籍ID           |



```json
{
    "code": 200,
    "message": "Success: get /admin/books/borrowing-info",
    "data": [
        {
            "username": "user1",
            "borrowDate": "2024-03-20",
            "dueDate": "2024-04-20",
            "instanceId": 1
        },
        {
            "username": "user2",
            "borrowDate": "2024-03-18",
            "dueDate": "2024-04-18",
            "instanceId": 2
        }
    ]
}

```





```json
{
    "code": 400,
    "message": "Fail: isbn not found",
    "data":null
}
```



```json
{
    "code": 400,
    "message": "Fail: borrowing info is null or empty",
    "data":null
}
```



## 增加新图书信息

**接口地址**



| 方法 | 地址              |
| ---- | ----------------- |
| POST | /admin/books/info |



**请求体参数**

| 参数        | 类型   | 是否必需 | 描述             |
| ----------- | ------ | -------- | ---------------- |
| isbn        | string | 是       | 新图书的ISBN号   |
| title       | string | 是       | 新图书的标题     |
| author      | string | 是       | 新图书的作者     |
| cover       | blob   | 是       | 新图书的封面图片 |
| description | string | 是       | 新图书的描述     |



```json
{
    "isbn": "978-3-16-148410-2",
    "title": "New Book Title",
    "author": "New Author",
    "cover": "/9j/4AAQSkZJRgABAQEAYABgAAD/...",
    "description": "Description of New Book"
}
```



**响应参数**

| 参数    | 类型   | 描述           |
| ------- | ------ | -------------- |
| code    | int    | 状态码         |
| message | string | 提示信息       |
| data    | object | 新增的图书信息 |



```json
{
    "code": 200,
    "message": "Success: post /admin/books/info",
    "data": null
}
```



```json
{
    "code": 400,
    "message": "Fail: isbn not null",
    "data":null
}
```





```json
{
    "code": 400,
    "message": "Fail: title not null",
    "data":null
}
```





```json
{
    "code": 400,
    "message": "Fail: info already exist",
    "data":null
}
```



超过数据库限制

```json
{
    "code": 400,
    "message": "Fail: data too long",
    "data":null
}
```



## 删除图书信息

**接口地址**

| 方法   | 地址                     |
| ------ | ------------------------ |
| DELETE | /admin/books/info/{isbn} |



**请求体参数**

无



**响应参数**

| 参数    | 类型   | 描述     |
| ------- | ------ | -------- |
| code    | int    | 状态码   |
| message | string | 提示信息 |
| data    | object | 删除结果 |



```json
{
    "code": 200,
    "message": "Success: delete /admin/books/info/{isbn}",
    "data": null
}
```



```json
{
    "code": 400,
    "message": "Fail: isbn not found",
    "data":null
}
```





```json
{
    "code": 400,
    "message": "Fail: isbn not null",
    "data":null
}
```



```json
{
    "code": 400,
    "message": "Fail: can not delete the book info",
    "data":null
}
```



## 修改图书信息

**接口地址**

| 方法 | 地址                     |
| ---- | ------------------------ |
| PUT  | /admin/books/info/{isbn} |



**请求体参数**

| 参数        | 类型   | 是否必需 | 描述             |
| ----------- | ------ | -------- | ---------------- |
| title       | string | 否       | 新的标题         |
| author      | string | 否       | 新的作者         |
| cover       | blob   | 否       | 新的封面图片数据 |
| description | string | 否       | 新的书籍描述     |



```json
{
    "title": "New Title",
    "author": "New Author",
    "cover": "/9j/4AAQSkZJRgABAQEAYABgAAD/...",
    "description": "New Description"
}

```



**响应参数**

| 参数    | 类型   | 描述     |
| ------- | ------ | -------- |
| code    | int    | 状态码   |
| message | string | 提示信息 |
| data    | object | 修改结果 |



```json
{
    "code": 200,
    "message": "Success: put /admin/books/info/{isbn}",
    "data": null
}
```



```json
{
    "code": 400,
    "message": "Fail: bad request",
    "data":null
}
```





```json
{
    "code": 400,
    "message": "Fail: isbn not found",
    "data":null
}
```



## 增加图书实体

**接口地址**



| 方法 | 地址                   |
| ---- | ---------------------- |
| POST | /admin/books/instances |



**请求体参数**

| 参数   | 类型   | 是否必需 | 描述           |
| ------ | ------ | -------- | -------------- |
| isbn   | string | 是       | 图书的ISBN号   |
| number | int    | 是       | 添加图书的数量 |



```json
{
    "isbn": "978-3-16-148410-0",
    "number": 5
}
```



**响应参数**

| 参数    | 类型   | 描述         |
| ------- | ------ | ------------ |
| code    | int    | 状态码       |
| message | string | 提示信息     |
| data    | object | 操作结果信息 |



```json
{
    "code": 200,
    "message": "Success: post /admin/books/instances",
    "data": null
}

```



```json
{
    "code": 400,
    "message": "Fail:can not add book instance",
    "data":null
}
```



## 删除图书实体

**接口地址**



| 方法   | 地址                                |
| ------ | ----------------------------------- |
| DELETE | /admin/books/instances/{instanceId} |



**请求体参数**

无



**响应参数**



| 参数    | 类型   | 描述         |
| ------- | ------ | ------------ |
| code    | int    | 状态码       |
| message | string | 提示信息     |
| data    | object | 操作结果信息 |



```json
{
    "code": 200,
    "message": "Success: delete /admin/books/instances/{instanceId}",
    "data": null
}
```



```json
{
    "code": 400,
    "message": "Fail: instanceId not found",
    "data":null
}
```



## ~~查看借阅申请~~

**接口地址**



| 方法 | 地址                          |
| ---- | ----------------------------- |
| GET  | /admin/borrowing/applications |



**请求参数**

| 参数  | 类型 | 是否必需 | 描述          |
| ----- | ---- | -------- |-------------|
| approved | int  | 是       | 0为未审批，1为已审批 |



**Query Params**



```
/admin/borrowing/applications?approved=0
```



**响应参数**

| 参数                     | 类型   | 描述                                     |
| ------------------------ | ------ | ---------------------------------------- |
| code                     | int    | 状态码                                   |
| message                  | string | 提示信息                                 |
| data                     | array  | 借阅申请列表                             |
| data[i].borrowingId      | int    | 借阅记录ID                               |
| data[i].userId           | int    | 用户ID                                   |
| data[i].username         | string | 用户名                                   |
| data[i].instanceId       | int    | 图书实体ID                               |
| data[i].isbn             | string | 图书ISBN号                               |
| data[i].borrowDate       | string | 借阅日期                                 |
| data[i].dueDate          | string | 应归还日期                               |
| data[i].borrowAprvStatus | int    | 借阅申请的审批状态，0为未审批，1为已审批 |



```json
{
    "code": 200,
    "message": "Success: get /admin/borrowing/applications",
    "data": [
        {
            "borrowingId": 1,
            "userId": 456,
            "username": "test_user",
            "instanceId": 123,
            "isbn": "1234567890123",
            "borrowDate": "2024-03-21",
            "dueDate": "2024-04-21",
            "borrowAprvStatus":0,
            "location":"B306"
        },
        {
            "borrowingId": 2,
            "userId": 789,
            "username": "another_user",
            "instanceId": 456,
            "isbn": "4567890123456",
            "borrowDate": "2024-03-22",
            "dueDate": "2024-04-22",
            "borrowAprvStatus":1,
            "location":"B306"
        }
    ]
}
```



## ~~查看迟还申请~~

**接口地址**

| 方法 | 地址                          |
| ---- | ----------------------------- |
| GET  | /admin/borrowing/late-returns |



**请求参数**


| 参数  | 类型 | 是否必需 | 描述          |
| ----- | ---- | -------- |-------------|
| approved | int  | 是       | 0为未审批，1为已审批 |



**Query Params**



```
/admin/borrowing/late-returns?approved=0
```




**响应参数**

| 参数                      | 类型   | 描述                                     |
| ------------------------- | ------ | ---------------------------------------- |
| code                      | int    | 状态码                                   |
| message                   | string | 提示信息                                 |
| data                      | array  | 迟还申请列表                             |
| data[i].borrowingId       | int    | 借阅记录ID                               |
| data[i].userId            | int    | 用户ID                                   |
| data[i].username          | string | 用户名                                   |
| data[i].instanceId        | int    | 图书实体ID                               |
| data[i].isbn              | string | 图书ISBN号                               |
| data[i].borrowDate        | string | 借阅日期                                 |
| data[i].dueDate           | string | 应归还日期                               |
| data[i].lateRetDate       | string | 迟还日期                                 |
| data[i].lateRetAprvStatus | int    | 迟还申请的审批状态，0为未审批，1为已审批 |



```json
{
    "code": 200,
    "message": "Success: get /admin/borrowing/late-returns",
    "data": [
        {
            "borrowingId": 1,
            "userId": 456,
            "username": "test_user",
            "instanceId": 123,
            "isbn": "1234567890123",
            "borrowDate": "2024-03-21",
            "dueDate": "2024-04-21",
            "lateRetDate":"2024-05-21",
            "lateRetAprvStatus":0
        },
        {
            "borrowingId": 2,
            "userId": 789,
            "username": "another_user",
            "instanceId": 456,
            "isbn": "4567890123456",
            "borrowDate": "2024-03-22",
            "dueDate": "2024-04-22",
            "lateRetDate":"2024-05-22",
			"lateRetAprvStatus":1
        }
    ]
}

```





## 还书确认 ##

**接口地址**

| 方法 | 地址                                 |
| ---- | ------------------------------------ |
| PUT  | /admin/borrowing/return/{instanceId} |



**请求体参数**

无



**响应参数**

| 参数    | 类型   | 描述             |
| ------- | ------ | ---------------- |
| code    | int    | 状态码           |
| message | string | 提示信息         |
| data    | object | 处理后的还书信息 |



## ~~处理借阅申请~~

**接口地址**

| 方法 | 地址                                        |
| ---- | ------------------------------------------- |
| PUT  | /admin/borrowing/applications/{borrowingId} |



**请求体参数**

| 参数  | 类型 | 是否必需 | 描述         |
| ----- | ---- | -------- |------------|
| agree | int  | 是       | 1为同意，0为不同意 |



```json
{
    "agree": 1
}
```



**响应参数**



| 参数    | 类型   | 描述                 |
| ------- | ------ | -------------------- |
| code    | int    | 状态码               |
| message | string | 提示信息             |
| data    | object | 处理后的借阅申请信息 |



```json
{
    "code": 200,
    "message": "Success: put /admin/borrowing/applications/{borrowingId}",
    "data": null
}
```



```json
{
    "code": 400,
    "message": "Fail: not found this borrow approval",
    "data": null
}
```

```json
{
    "code": 400,
    "message": "Fail: already already processed",
    "data": null
}
```

```json
{
    "code": 400,
    "message": "Fail: input error",
    "data": null
}
```



## ~~处理迟还申请~~

**接口地址**



| 方法 | 地址                                        |
| ---- | ------------------------------------------- |
| PUT  | /admin/borrowing/late-returns/{borrowingId} |



**请求体参数**

| 参数  | 类型 | 是否必需 | 描述               |
| ----- | ---- | -------- | ------------------ |
| agree | int  | 是       | 1为同意，0为不同意 |



```json
{
    "agree": 1
}
```



**响应参数**

```json
{
    "code": 200,
    "message": "Success: put /admin/borrowing/late-returns/{borrowingId}",
    "data": null
}
```



```json
{
    "code": 400,
    "message": "Fail: already already processed",
    "data": null
}
```

```json
{
    "code": 400,
    "message": "Fail: input error",
    "data": null
}
```

```json
{
    "code": 400,
    "message": "Fail: not found this borrow approval",
    "data": null
}
```



TODO 改名未归还的申请？或者修改方法？

## 查看未归还的读者列表

**接口地址**

| 方法 | 地址                             |
| ---- | -------------------------------- |
| GET  | /admin/borrowing/overdue-readers |



**请求体参数**

无



**响应参数**

| 参数               | 类型   | 描述           |
| ------------------ | ------ | -------------- |
| code               | int    | 状态码         |
| message            | string | 提示信息       |
| data               | array  | 未归还读者列表 |
| data[i].userId     | int    | 用户ID         |
| data[i].username   | string | 用户名         |
| data[i].email      | string | 邮箱地址       |
| data[i].borrowDate | string | 借阅日期       |
| data[i].dueDate    | string | 应归还日期     |
| data[i].isbn       | string | isbn           |
| data[i].instanceId | int    | 书籍实体编号   |



```json
{
    "code": 200,
    "message": "Success: get /admin/borrowing/overdue-readers",
    "data": [
        {
            "userId": 123,
            "username": "user1",
            "email": "user1@example.com",
            "borrowDate": "2024-03-21",
            "dueDate": "2024-04-21"
        },
        {
            "userId": 456,
            "username": "user2",
            "email": "user2@example.com",
            "borrowDate": "2024-03-22",
            "dueDate": "2024-04-22"
        }
    ]
}
```



## 检索读者

**接口地址**

| 方法 | 地址                  |
| ---- | --------------------- |
| GET  | /admin/readers/search |



**请求参数**

| 参数       | 类型     | 是否必需 | 描述              |
|----------|--------|------|-----------------|
| username | String | 否    | 用户名             |
| userId   | int    | 否    | 用户id            |

==只按其中一种==



**Query Params**



```
/admin/readers/search?username=test_user
/admin/readers/search?userId=1
```





**响应参数**

| 参数                | 类型   | 描述                                       |
| ------------------- | ------ | ------------------------------------------ |
| code                | int    | 状态码                                     |
| message             | string | 提示信息                                   |
| data                | array  | 匹配的读者列表                             |
| data[i].userId      | int    | 用户ID                                     |
| data[i].username    | string | 用户名                                     |
| data[i].email       | string | 用户邮箱                                   |
| data[i].avatar      | blob   | 用户头像图片数据                           |
| data[i].borrowPerms | int    | 借阅权限，1表示正常，0表示不能借阅所有书籍 |



```json
{
    "code": 200,
    "message": "Success: get /admin/readers/search",
    "data": [
        {
            "userId": 123,
            "username": "test1",
            "email": "test1@example.com",
            "avatar": "/9j/4AAQSkZJRgABAQEAYABgAAD/...",
            "borrowPerms": 1
        },
        {
            "userId": 456,
            "username": "test2",
            "email": "test2@example.com",
            "avatar": "/9j/4AAQSkZJRgABAQEAYABgAAD/...",
            "borrowPerms": 0
        }
    ]
}
```



```json
{
    "code": 400,
    "message": "Fail: bad request",
    "data":null
}
```



## ==处分读者==

**接口地址**

| 方法 | 地址                    |
| ---- | ----------------------- |
| PUT  | /admin/penalty/{userId} |



**请求体参数**

| 参数   | 类型       | 是否必需 | 描述     |
| ------ | ---------- | -------- | -------- |
| reason | string     | 是       | 扣钱理由 |
| money  | ==string== | 是       | 扣钱数额 |



```json
{
  "reason": "Late return of books",
  "money": 1
}
```



**响应参数**

| 参数    | 类型   | 描述             |
| ------- | ------ | ---------------- |
| code    | int    | 状态码           |
| message | string | 提示信息         |
| data    | object | 处理后的读者信息 |



```json
{
    "code": 200,
    "message": "Success: put /admin/penalty/{userId}",
    "data": null
}
```



```json
{
    "code": 400,
    "message": "Fail: invalid date",
    "data":null
}
```



```json
{
    "code": 400,
    "message": "Fail: no such user",
    "data":null
}
```



# 读者



## 个人中心信息显示

## 修改个人信息

## 图书目录

## 图书检索

以上同管理员


## 借阅图书

**接口地址**

| 方法 | 地址            |
| ---- | --------------- |
| POST | /user/borrowing |



**请求体参数**

| 参数 | 类型   | 是否必需 | 描述 |
| ---- | ------ | -------- | ---- |
| isbn | string | 是       |      |



```json
{
    "isbn": "978-3-16-148410-0"
}
```



**响应参数**

| 参数            | 类型   | 描述                     |
| --------------- | ------ | ------------------------ |
| code            | int    | 状态码                   |
| message         | string | 提示信息                 |
| data            | object | 数据                     |
| data.instanceId | int    | 借阅的书实体ID（哪一本） |
| data.location   | string | 借阅图书位置             |
| data.duedate    | string | 借阅到期时间             |



```json
{
    "code": 200,
    "message": "Success: post /user/borrowing",
    "data": {
        "instanceId": 6,
        "dueDate": "2024-05-02",
        "location": "A-6"
    }
}
```



```json
{
    "code": 400,
    "message": "Fail: do not have borrowing privileges",
    "data":null
}
```



```json
{
    "code": 400,
    "message": "the book has been borrowed",
    "data":null
}
```



```json
{
    "code": 400,
    "message": "Fail: invalid date",
    "data":null
}
```

tip: borrowDate为当前时间



## ~~归还图书~~

**接口地址**

| 方法 | 地址                                |
| ---- | ----------------------------------- |
| PUT  | /user/borrowing/return/{instanceId} |



**请求体参数**

无



**响应参数**

| 参数    | 类型   | 描述         |
| ------- | ------ | ------------ |
| code    | int    | 状态码       |
| message | string | 提示信息     |
| data    | object | 预约记录信息 |



```json
{
    "code": 200,
    "message": "Success: put /user/borrowing/return/{instanceId}",
    "data": null
}
```



```json
{
    "code": 400,
    "message": "Fail: instanceId not found",
    "data":null
}
```



```json
{
    "code": 400,
    "message": "Fail: borrowing not found",
    "data":null
}
```



## 迟还图书



**接口地址**

| 方法 | 地址                          |
| ---- | ----------------------------- |
| POST | /user/borrowing/lateretBorrow |



**请求体参数**

| 参数     | 类型 | 是否必需 | 描述       |
| -------- | ---- | -------- | ---------- |
| borrowId | int  | 是       | 借阅记录Id |

```json
{
    "borrowId": 2
}
```

**响应参数**

| 参数          | 类型       | 描述         |
| ------------- | ---------- | ------------ |
| code          | int        | 状态码       |
| message       | string     | 提示信息     |
| data          | object     | 数据         |
| data.username | string     | 用户名字     |
| data.money    | BigDecimal | 用户剩余金额 |



```json
{
    "code": 200,
    "message": "Success: post /admin/borrowing/lateretBorrow",
    "data": {
        "money": 86.00
    }
}
```



## ~~迟还申请~~



**接口地址**

| 方法 | 地址                            |
| ---- | ------------------------------- |
| POST | /user/borrowing/lateret-request |



**请求体参数**

| 参数     | 类型 | 是否必需 | 描述       |
| -------- | ---- | -------- | ---------- |
| borrowId | int  | 是       | 借阅记录Id |

```json
{
    "borrowId": 2
}
```

**响应参数**

| 参数    | 类型   | 描述     |
| ------- | ------ | -------- |
| code    | int    | 状态码   |
| message | string | 提示信息 |
| data    | object | 数据     |



```json
{
    "code": 200,
    "message": "Success: post /admin/borrowing/lateret-request",
    "data": null
}
```







## 预约图书

**接口地址**

| 方法 | 地址                     |
| ---- | ------------------------ |
| PUT  | /user/reservation/{isbn} |



**请求体参数**

无





**响应参数**

| 参数    | 类型   | 描述         |
| ------- | ------ | ------------ |
| code    | int    | 状态码       |
| message | string | 提示信息     |
| data    | object | 预约记录信息 |



```json
{
    "code": 200,
    "message": "Success: put /user/reservation/{userId}",
    "data": null
}
```



```json
{
    "code": 400,
    "message": "Fail: bad request",
    "data":null
}
```



```json
{
    "code": 400,
    "message": "Fail: has been reserved",
    "data":null
}
```



## 用户状态

**接口地址**



| 方法 | 地址         |
| ---- | ------------ |
| GET  | /user/status |



**请求体参数**



无



**响应参数**



| 参数             | 类型       | 描述               |
| ---------------- | ---------- | ------------------ |
| userId           | int        | 用户ID             |
| username         | string     | 用户名             |
| email            | string     | 邮箱地址           |
| data.money       | bigdecimal | 用户余额           |
| data.borrowPerms | int        | 用户还能借阅几本书 |



```json
{
    "code": 200,
    "message": "Success: get /user/status",
    "data": {
        "borrowPerms": 0,
        "money": 86.00
    }
}
```



## 用户查看自己的借阅记录



**接口地址**

| 方法 | 地址                    |
| ---- | ----------------------- |
| GET  | /user/borrowing/records |



**请求体参数**

无



**Query Params**

| 参数   | 类型 | 是否必需 | 描述                                     |
| ------ | ---- | -------- | ---------------------------------------- |
| status | int  | 是       | 借阅状态<br />已经归还（0）、未归还（1） |

```
已经归还(0)
未归还(1)	
```





```
/user/borrowing/records?status=0
```





**响应参数**

| 参数                     | 类型   | 描述                                     |
| ------------------------ | ------ | ---------------------------------------- |
| code                     | int    | 状态码                                   |
| message                  | string | 提示信息                                 |
| data                     | array  | 借阅记录列表                             |
| data[i].borrowingId      | int    | 借阅记录ID                               |
| data[i].userId           | int    | 用户ID                                   |
| data[i].instanceId       | int    | 图书实体ID                               |
| data[i].isbn             | string | 图书ISBN号                               |
| data[i].borrowDate       | string | 借阅日期                                 |
| data[i].dueDate          | string | 应归还日期                               |
| data[i].borrowAprvStatus | int    | 借阅申请的审批状态，0为未审批，1为已审批 |



```json
{
    "code": 200,
    "message": "Success: get /user/borrowing/records",
    "data": [
        {
            "borrowingId": 1,
            "userId": "1",
            "instanceId": 123,
            "isbn": "1234567890123",
            "title":"ttt",
            "borrowDate": "2024-03-21",
            "dueDate": "2024-04-21",
            "borrowAprvStatus": 1
        },
        {
            "borrowingId": 2,
            "userId": "2",
            "instanceId": 456,
            "isbn": "4567890123456",
            "title":"ttt7",
            "borrowDate": "2024-03-22",
            "dueDate": "2024-04-22",
            "borrowAprvStatus": 0
        }
    ]
}
```



## ==用户查看自己的处分记录==

**接口地址**

| 方法 | 地址          |
| ---- | ------------- |
| GET  | /user/penalty |



**请求体参数**

无



**响应参数**

| 参数               | 类型       | 描述           |
| ------------------ | ---------- | -------------- |
| code               | int        | 状态码         |
| message            | string     | 提示信息       |
| data               | array      | 处分记录列表   |
| data[i].penaltyId  | int        | 处分记录ID     |
| data[i].adminId    | int        | 管理员ID       |
| data[i].adminName  | string     | 管理员用户名   |
| data[i].adminEmail | int        | 管理员邮箱     |
| data[i].userId     | int        | 用户Id         |
| data[i].username   | string     | 被处分人用户名 |
| data[i].reason     | string     | 处分原因       |
| data[i].money      | BigDecimal | 扣钱数量       |



```json
{
    "code": 200,
    "message": "Success: get /user/penalty",
    "data": [
        {
            "penaltyId": 1,
            "adminId": 1,
            "adminName": "jia",
            "adminEmail": "123456789@qq.com",
            "userId": 1,
            "username": "jia",
            "reason": "Late return of books",
            "money": 1
        },
        {
            "penaltyId": 2,
            "adminId": 1,
            "adminName": "jia",
            "adminEmail": "123456789@qq.com",
            "userId": 1,
            "username": "jia",
            "reason": "Late return of books",
            "money": 1
        },
        {
            "penaltyId": 3,
            "adminId": 1,
            "adminName": "jia",
            "adminEmail": "123456789@qq.com",
            "userId": 1,
            "username": "jia",
            "reason": "Late return of books",
            "money": 1
        },
        {
            "penaltyId": 4,
            "adminId": 1,
            "adminName": "jia",
            "adminEmail": "123456789@qq.com",
            "userId": 1,
            "username": "jia",
            "reason": "Late return of books",
            "money": 1
        },
        {
            "penaltyId": 5,
            "adminId": 1,
            "adminName": "jia",
            "adminEmail": "123456789@qq.com",
            "userId": 1,
            "username": "jia",
            "reason": "Late return of books",
            "money": 1
        },
        {
            "penaltyId": 6,
            "adminId": 1,
            "adminName": "jia",
            "adminEmail": "123456789@qq.com",
            "userId": 1,
            "username": "jia",
            "reason": "Late return of books",
            "money": 1
        }
    ]
}
```



## 查看预约



**接口地址**

| 方法 | 地址              |
| ---- | ----------------- |
| GET  | /user/reservation |



**请求体参数**

无



**响应参数**

| 参数                | 类型   | 描述         |
| ------------------- | ------ | ------------ |
| code                | int    | 状态码       |
| message             | string | 提示信息     |
| data                | array  | 图书目录数组 |
| data[i].isbn        | string | ISBN号       |
| data[i].title       | string | 标题         |
| data[i].author      | string | 作者         |
| data[i].cover       | blob   | 封面图片数据 |
| data[i].description | string | 描述         |
| data[i].available   | int    | 现存数量     |
| data[i].borrowed    | int    | 被借阅数量   |



```json
{
    "code": 200,
    "message": "Success: get /user/reservation",
    "data": [
        {
            "isbn": "978-3-16-148410-0",
            "title": "Book Title 1",
            "author": "Author 1",
            "cover": "/9j/4AAQSkZJRgABAQEAYABgAAD/...",
            "description": "Description of Book 1",
            "location": "B333",
            "available": 5,
            "borrowed": 10
        },
        {
            "isbn": "978-3-16-148410-1",
            "title": "Book Title 2",
            "author": "Author 2",
            "cover": "/9j/4AAQSkZJRgABAQEAYABgAAD/...",
            "description": "Description of Book 2",
            "location": "B333",
            "available": 3,
            "borrowed": 8
        }
    ]
}

```





## 取消预约

**接口地址**

| 方法 | 地址                            |
| ---- | ------------------------------- |
| PUT  | /user/reservation/cancel/{isbn} |



**请求体参数**

无



**响应参数**

| 参数    | 类型   | 描述     |
| ------- | ------ | -------- |
| code    | int    | 状态码   |
| message | string | 提示信息 |
| data    | object | null     |



```json
{
    "code": 200,
    "message": "Success: put /user/reservation/cancel/{isbn}",
    "data": null
}
```





```json
{
    "code": 200,
    "message": "Fail: not reserved",
    "data": null
}
```





