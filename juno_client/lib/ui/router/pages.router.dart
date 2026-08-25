import 'package:go_router/go_router.dart';
import 'package:juno_client/config/app/app.tools.dart';
import 'package:juno_client/ui/pages/auth/layout/loginDesktop.layout.dart';
import 'package:juno_client/ui/pages/auth/layout/loginMovile.layout.dart';
import 'package:juno_client/ui/pages/auth/login.page.dart';
import 'package:juno_client/ui/pages/home/home.page.dart';
import 'package:juno_client/ui/pages/home/view/homeDesktop.view.dart';
import 'package:juno_client/ui/pages/home/view/homeMovile.view.dart';

final GoRouter router = GoRouter(
    initialLocation: '/login',
    routes: [
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
          GoRoute(
            path: '/home',
            builder: (context, state) => isDesktop ? HomeDesktopView() : HomeMovileView(), 
          )
        ]
      ),
    ]
);