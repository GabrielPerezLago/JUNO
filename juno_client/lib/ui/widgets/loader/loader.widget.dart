import 'package:flutter/material.dart';
import 'package:juno_client/config/app/app.tools.dart';

class JnLoader extends StatelessWidget {

  @override
  Widget build(BuildContext context) => Center(
    child: CircularProgressIndicator(
      color: junoColorScheme(context).secondary,
    ),
  );

}