import 'package:flutter/material.dart';
import 'package:juno_client/config/app/app.tools.dart';

class JnLogoLoder extends StatelessWidget {

  @override
  Widget build(BuildContext context) => Container(
    color: Colors.blueGrey,
    child: Stack(
      alignment: Alignment.center,
      children: [
        ClipOval(
          child: Image.asset(
            'assets/images/logo.png', 
            width: width(context) * 0.1,
            fit: BoxFit.cover
          ),
        ),
        Center(
          child: CircularProgressIndicator(
            constraints: BoxConstraints(
              minWidth: width(context) * 0.2,
              maxWidth: width(context) * 0.5,
              minHeight: height(context) * 0.2,
              maxHeight: height(context) * 0.5
            ),
            color: junoColorScheme(context).secondary,
            backgroundColor: Colors.transparent,
            strokeCap:  StrokeCap.round,
            strokeWidth: 4.0,
          ),
        )
      ],
    ),
  );

}