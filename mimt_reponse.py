import json
from mitmproxy import http


def read_config(file_path):
    """
        读取配置文件
        配置文件为json，例子：
        {
            "/path1": {"status": "0", "msg": "", "data": {}},
            "/path2": {"key2": "value2"}
        }
    """
    with open(file_path, "r", encoding="utf-8") as f:
        data = f.read()
        if data:
            return json.loads(data)
        return {}


def response(flow: http.HTTPFlow):
    # 修改响应数据
    try:
        path_data = read_config("mimt_config.json")
        for key, value in path_data:
            if key in flow.request.path:
                print(f"修改响应数据: {flow.request.path}")
                flow.response = http.Response.make(
                    200,
                    json.dumps(value),
                    {"Content-Type": "application/json"}
                )
                return

    except Exception as e:
        print("修改响应数据异常: " + str(e))
        pass
