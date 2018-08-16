# goosgbt

## Configure OpenFire server

Pull the **gizmotronic/openfire** docker image with command
`docker pull gizmotronic/openfire:4.2.3`

Created some local folder on your system, for example `~/example/folder/openfire`

Create openfire container with the following command. The `openfire` name can be whatever you wish.  Make sure the folder after `--volume` matches folder you created above
```
docker run --name openfire -d --restart=always \
  --publish 9090:9090 --publish 5222:5222 --publish 7777:7777 \
  --volume ~/example/folder/openfire:/var/lib/openfire \
  gizmotronic/openfire:4.2.3
```

You can now launch openfire admin console with url: http://localhost:9090

You can stop container with command `docker stop openfire` where **openfire** matches name of container in command above

You can start container with command `docker start openfire` where **openfire** matches name of container in command above

For details on the Openfire container see: https://hub.docker.com/r/gizmotronic/openfire/

-----------

## Configure workspace

