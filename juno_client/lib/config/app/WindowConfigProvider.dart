
import 'package:flutter/material.dart';
import 'package:juno_client/config/app/app.tools.dart';
import 'package:window_manager/window_manager.dart';

class WindowConfigProvider {

Future<void> exectue() async {
    if (isDesktop) {

      await windowManager.ensureInitialized();

      windowManager.waitUntilReadyToShow(windowOptions(), () async {
        await windowManager.show();
        await windowManager.focus();
      });

    }
 }

static  WindowOptions windowOptions() => const WindowOptions(
    size: Size(1280, 720),          
    minimumSize: Size(100, 600),   
    center: true,
    backgroundColor: Colors.transparent,
    skipTaskbar: false,
    titleBarStyle: TitleBarStyle.normal,
  );

}