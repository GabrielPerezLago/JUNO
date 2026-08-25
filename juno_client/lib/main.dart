import 'dart:async';
import 'package:flutter/material.dart';
import 'package:http/src/response.dart';
import 'package:juno_client/config/app/WindowConfigProvider.dart';
import 'package:juno_client/infraestructure/repositories/implements/HttpReqRepository.dart';
import 'package:juno_client/ui/router/pages.router.dart';
import 'package:juno_client/config/theme/JunoThemeProvider.dart';
import 'package:window_manager/window_manager.dart';

void main() {
  try {
    final  windConf = WindowConfigProvider();
    windConf.exectue().then((void window) { print('window');});
  } catch (ex) {
    print( 'Error Window Cofiguration : $ex');
  }
  
  try {
    HttpReqRepository.GET('/test')
    .then((Response res) => res.statusCode == 200 ? print('Conexion Establecida') : print('Conexion NO Establecida') );
  } catch (ex) {
    print('Error al establecer conexion: $ex');
  }

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