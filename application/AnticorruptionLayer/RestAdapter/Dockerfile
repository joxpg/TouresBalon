

FROM mcr.microsoft.com/dotnet/core/aspnet:3.1

COPY bin/Release/netcoreapp3.1/  App/
WORKDIR /App
ENTRYPOINT ["dotnet", "RestAdapter.dll"]