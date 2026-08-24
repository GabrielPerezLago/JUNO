import 'dart:io';
import 'package:flutter/material.dart';

final isDesktop = Platform.isMacOS || Platform.isWindows || Platform.isLinux;
final isMovile = Platform.isAndroid || Platform.isIOS;

bool isDark(BuildContext context) => Theme.of(context).brightness == Brightness.dark;
double width(BuildContext context) => MediaQuery.of(context).size.width;
double height(BuildContext context) => MediaQuery.of(context).size.height;


ColorScheme junoColorScheme(BuildContext context) => Theme.of(context).colorScheme;