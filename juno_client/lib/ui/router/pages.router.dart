import 'package:go_router/go_router.dart';
import 'package:juno_client/config/app/app.tools.dart';
import 'package:juno_client/ui/pages/auth/layout/loginDesktop.layout.dart';
import 'package:juno_client/ui/pages/auth/layout/loginMovile.layout.dart';
import 'package:juno_client/ui/pages/auth/login.page.dart';

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
      )
    ]
);