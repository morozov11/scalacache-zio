package scalacache.zio

import scalacache.{Async, Mode}
import zio.{Promise, RIO, ZIO}


object ZIOEffect {

  object mode {

    //A mode that wraps computations in ZIO Effect

    implicit def mode[R]: Mode[RIO[R, *]] = new Mode[RIO[R, *]] {
      val M: Async[RIO[R, *]] = new AsyncZIO[R].async
    }

  }

  class AsyncZIO[R] {

    type G[A] = RIO[R, A]

    def async: Async[G] = new Async[G] {

      def pure[A](a: A): G[A] = ZIO.succeed(a)

      def map[A, B](fa: G[A])(f: A => B): G[B] = fa.map(f)

      def flatMap[A, B](fa: G[A])(f: A => G[B]): G[B] = fa.flatMap(f)

      def delay[A](thunk: => A): G[A] = ZIO.apply(thunk)

      def suspend[A](thunk: => G[A]): G[A] = thunk

      def raiseError[A](t: Throwable): G[A] = ZIO.fail(t)

      def async[A](register: (Either[Throwable, A] => Unit) => Unit): G[A] = {
        Promise.make[Throwable, A].flatMap { promise =>
          ZIO.effectAsync { k =>
            register(either => k(promise.await *> ZIO.fromEither(either)))
          }
        }
      }

      def handleNonFatal[A](fa: => G[A])(f: Throwable => A): G[A] = {
        fa.catchAll(e => ZIO.succeed(f(e)))
      }
    }

  }

}
