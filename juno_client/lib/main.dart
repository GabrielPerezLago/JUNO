
import 'dart:async';

import 'package:flutter/material.dart';
import 'package:http/src/response.dart';
import 'package:juno_client/infraestructure/repositories/implements/HttpReqRepository.dart';
import 'package:juno_client/ui/router/pages.router.dart';
import 'package:juno_client/config/theme/JunoThemeProvider.dart';

void main() {

  HttpReqRepository.GET('/test')
  .then((Response res) => res.statusCode == 200 ? print('Conexion Establecida') : print('Conexion NO Establecida') );


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