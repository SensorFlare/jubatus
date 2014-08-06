jubatus
=======

Ubuntu Server 12.04 LTS (64-bit)
Write the following line to /etc/apt/sources.list.d/jubatus.list to register Jubatus Apt repository to the system.

deb http://download.jubat.us/apt binary/

=======
Now install jubatus package.

$ sudo apt-get update
$ sudo apt-get install jubatus

Install these packages without verification [y/N]? y
Now Jubatus is installed in /opt/jubatus.

=======

Each time before using Jubatus, you need to load the environment variable from profile script.

$ source /opt/jubatus/profile
