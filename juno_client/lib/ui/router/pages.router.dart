import 'package:go_router/go_router.dart';
import 'package:juno_client/config/app/app.tools.dart';
import 'package:juno_client/ui/pages/auth/layout/loginDesktop.layout.dart';
import 'package:juno_client/ui/pages/auth/layout/loginMovile.layout.dart';
import 'package:juno_client/ui/pages/auth/login.page.dart';
import 'package:juno_client/ui/pages/errors/error.view.dart';
import 'package:juno_client/ui/pages/home/home.page.dart';
import 'package:juno_client/ui/pages/home/view/homeDesktop.view.dart';
import 'package:juno_client/ui/pages/home/view/homeMovile.view.dart';

GoRouter router({required String initialDirection}) => GoRouter(
    initialLocation: initialDirection,
    routes: [
      GoRoute(
        path: '/con-error',
        builder: (context, state) => ErrorView(),
      ),
      ShellRoute(
        builder: (context, state, child) => LoginViewPage(child: child,),
        routes: [
          GoRoute(
            path: '/login',
            builder: (context, state) => isDesktop ? LoginDesktopLayout(): LoginMovileLayout(),
          )
        ]
      ),
      ShellRoute(
        builder: (context, state, child) => HomePage(child: child),
        routes: [
          ShellRoute(
            builder: (context, state, child) =>  HomeDesktopView(layout: child),
            routes: [
              GoRoute(path: '/home-usuario') ,
              GoRoute(path: '/home-direccion'),
              GoRoute(path: '/home-docente')
            ]
          )
        ]
      ),
    ]
);