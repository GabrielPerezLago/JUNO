import 'package:flutter/material.dart';
import 'package:juno_client/ui/router/pages.router.dart';
import 'package:juno_client/config/theme/JunoThemeProvider.dart';

void main() {
  runApp(const JunoClient());
}

class JunoClient extends StatelessWidget {

  const JunoClient({ super.key });

  @override
  Widget build(BuildContext context) => MaterialApp.router(
    debugShowCheckedModeBanner: false,
    routerConfig: router,
    themeMode: ThemeMode.system,
    theme: JunoThemeProvider.LIGHT,
    darkTheme: JunoThemeProvider.DARK,
  );

}