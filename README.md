# Tzu-Chieh Huang SpringCoreCoursework_20211212

---

[**題目**](#題目)

- [**2021/12/19 - Coursework 1**](#20211219---coursework-1)
- [**2021/12/26 - Coursework 2**](#20211226---coursework-2)
- [**2022/01/09 - Coursework 3**](#20220109---coursework-3)
- [**2022/01/23 - Coursework 4**](#20220123---coursework-4)


---

## 題目

### 2021/12/19 - Coursework 1

- [x] 請使用 **Java 8 stream** 進行資料分析並取得 mary 的老師有誰? (印出 name)

  程式碼: [CourseworkTest1.java](https://github.com/BazziHuang/SpringCoreCoursework_20211212/blob/main/src/test/java/pers/tzuchiehhuang/coursework/SpringCoreCoursework_20211212/coursework_1/CourseworkTest1.java)

  ### 2021/12/26 - Coursework 2

- [x] 完成 JsonDB.java 如果 person 已存在則 return false (name、age、birth 皆與目前資料庫某一 person 資料相同)

  程式碼: [JsonDB.java](https://github.com/BazziHuang/SpringCoreCoursework_20211212/blob/main/src/main/java/pers/tzuchiehhuang/coursework/SpringCoreCoursework_20211212/coursework_2/JsonDB.java)

- [x] 完成 PersonSystem.java 選項3 ~ 7資料分析與處理

  主程式: [PersonSystem.java](https://github.com/BazziHuang/SpringCoreCoursework_20211212/blob/main/src/main/java/pers/tzuchiehhuang/coursework/SpringCoreCoursework_20211212/coursework_2/PersonSystem.java)

  ### 2022/01/09 - Coursework 3

- [x] 將每次調用 查詢 queryAll() 方法的調用時間 Log 紀錄下來(透過切面導向程式設計 AOP)

  測試檔: [TemplateTest.java](https://github.com/BazziHuang/SpringCoreCoursework_20211212/blob/main/src/test/java/pers/tzuchiehhuang/coursework/SpringCoreCoursework_20211212/coursework_3/template/TemplateTest.java)  
  AOP: [JdbcLoggerAspect.java](https://github.com/BazziHuang/SpringCoreCoursework_20211212/blob/main/src/main/java/pers/tzuchiehhuang/coursework/SpringCoreCoursework_20211212/coursework_3/jdbc/logger/JdbcLoggerAspect.java)  
  Log檔(json): [log.json](https://github.com/BazziHuang/SpringCoreCoursework_20211212/blob/main/src/main/java/pers/tzuchiehhuang/coursework/SpringCoreCoursework_20211212/coursework_3/jdbc/logger/log.json)

  ### 2022/01/23 - Coursework 4

- [x] 建立交易紀錄 order_log 資料表，試問: 資料表應如何創建 (注意: 若 book 的 price 欄位有變動，order_log 則不影響)，請完成整段資料庫 log 寫入機制

  建立Log Table: [createLogTable.sql](https://github.com/BazziHuang/SpringCoreCoursework_20211212/blob/main/src/main/java/pers/tzuchiehhuang/coursework/SpringCoreCoursework_20211212/coursework_4/tx/sql/createLogTable.sql)  
  DAO寫orderLogger()方法: [BookDaoImpl.java](https://github.com/BazziHuang/SpringCoreCoursework_20211212/blob/main/src/main/java/pers/tzuchiehhuang/coursework/SpringCoreCoursework_20211212/coursework_4/tx/dao/BookDaoImpl.java)  
  Controller在finally區塊執行Logger: [BookController.java](https://github.com/BazziHuang/SpringCoreCoursework_20211212/blob/main/src/main/java/pers/tzuchiehhuang/coursework/SpringCoreCoursework_20211212/coursework_4/tx/controller/BookController.java)  
  測試檔: [BookControllerTest.java](https://github.com/BazziHuang/SpringCoreCoursework_20211212/blob/main/src/test/java/pers/tzuchiehhuang/coursework/SpringCoreCoursework_20211212/coursework_4/tx/controller/BookControllerTest.java)

  

