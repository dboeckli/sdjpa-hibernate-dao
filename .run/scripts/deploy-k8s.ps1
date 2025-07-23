cd target/helm/repo

$file = Get-ChildItem -Filter sdjpa-hibernato-dao-v*.tgz | Select-Object -First 1
tar -xvf $file.Name

$APPLICATION_NAME = Get-ChildItem -Directory | Where-Object { $_.LastWriteTime -ge $file.LastWriteTime } | Select-Object -ExpandProperty Name
helm upgrade --install $APPLICATION_NAME ./$APPLICATION_NAME --namespace sdjpa-hibernato-dao --create-namespace --wait --timeout 8m --debug --render-subchart-notes