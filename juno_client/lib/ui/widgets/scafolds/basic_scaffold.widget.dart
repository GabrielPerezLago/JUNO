import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:juno_client/config/app/app.tools.dart';

class JnBasicScaffold extends Scaffold{
  Widget child;
  BuildContext context;
  bool backGround;

  JnBasicScaffold({
    super.key, 
    required this.child,
    required this.context,
    super.backgroundColor,
    this.backGround = true
  });

  @override
  // TODO: implement body
  Widget? get body {
    return Stack(
      children: [
        if (backGround) Positioned.fill(
          child: Image.asset(isDark(context)? 'assets/images/collage.png' : 'assets/images/bg.png', fit: BoxFit.cover,),
        ),
        child
      ],
    );
  }

}