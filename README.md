# scalacache-zio  
[Scalacache](https://github.com/cb372/scalacache) zio effect instance  
Use just `import ZIOEffect.mode._`  
and then something like ` memoizeF[Task, Option[String]](Some(100 millis))`  
