aws --region eu-west-1 cloudformation deploy \
  --template codepipeline.yml \
  --capabilities CAPABILITY_NAMED_IAM \
  --stack-name awsfoodagg-Stack-codepipeline
