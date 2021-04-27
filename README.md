# scalacache-zio
Scalacache zio effect instance
Use just `import ZIOEffect.mode._`
and then something like ` memoizeF[Task, Option[String]](Some(100 millis))`
