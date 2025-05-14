**spec**

## 1. API

### 社員　- employee

#### 社員取得API
| GET | /api/employee/{employee_id} |
| --- | --------------------------- |

**Header**
| KEY             | VALUE                |
| --------------- | -------------------- |
| Content-Type    | application/json     |
| Authorization   | {access_token}       |

#### 社員検索API
| GET | /api/employee/search |
| --- | -------------------- |

**Header**
| KEY             | VALUE                |
| --------------- | -------------------- |
| Content-Type    | application/json     |
| Authorization   | {access_token}       |

#### 社員登録API
| POST | /api/employee/register      |
| ---- | --------------------------- |

**Header**
| KEY             | VALUE                |
| --------------- | -------------------- |
| Content-Type    | application/json     |
| Authorization   | {access_token}       |

#### 社員編集API
| PATCH | /api/employee/edit/{employee_id} |
| ----- | -------------------------------- |

**Header**
| KEY             | VALUE                |
| --------------- | -------------------- |
| Content-Type    | application/json     |
| Authorization   | {access_token}       |

#### 社員削除API
| DELETE | /api/employee/delete/{employee_id} |
| ------ | ---------------------------------- |

**Header**
| KEY             | VALUE                |
| --------------- | -------------------- |
| Content-Type    | application/json     |
| Authorization   | {access_token}       |

---

### レポート - report

#### レポート取得API
#### レポート検索API
#### レポート登録API
#### レポート編集API
#### レポート削除API
#### レポート提出API
#### レポート承認API
